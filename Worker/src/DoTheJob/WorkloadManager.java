package DoTheJob;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Properties;

import ZipFactoryPackage.ZipFactory;

import ErrorHandler.ExportError;
import JAXBManager.WorkloadInformation;

/**
 * Le WorkloadManager est une interface entre d'un coté la partie ecoute/ecriture du socket et la partie exécution de la workload
 * @author Ostro
 *
 */
public class WorkloadManager implements Runnable {


	private WorkloadInformation workload; 
	private BufferedWriter writer_system = null;   /////
	private BufferedWriter writer_error = null; 
	private WorkloadExec work;
	private Socket sock;


	public WorkloadManager (Socket sock){

		this.sock=sock;

	}
	/**
	 * Lance l'exécution et affiche les résultats et les erreurs rencontrées
	 */
	public void run () {

		Properties config=null;
		try{
			
			config = new Properties();
			FileInputStream in = new FileInputStream("Worker/config.properties");
			config.load(in);
			in.close();
			work = new WorkloadExec(CmdBuilder(), workload);
			work.setPath(getLocalStorageDirectory()+workload.getPath());
			Thread execThread = new Thread(work);

			execThread.start();
			while(execThread.isAlive()){

			}
			this.SendWhenFinish(sock);
		}
		catch(Exception e){
			ExportError.ExportErrorToFile(e);
		}
		
		if(work.isExecOk()){

			//do log for ok

			if(work.getOutputString().length()!=0){
				System.out.println("Output : \n"+work.getOutputString());  // plus besoin
				try {
					writer_system = new BufferedWriter ( new FileWriter ( new File(config.getProperty("path")+"/Workload/Results/system_out.txt")));
					writer_system.write(work.getOutputString());
					writer_system.close();
				}
				catch ( Exception e) {

					ExportError.ExportErrorToFile(e);

				}

			}


		}else{
			System.out.println("il y a une erreur");
			// do log for nok
			if(work.getOutputString().length()!=0){		//output de l'exécutable dans "system_out.txt"
				System.out.println("Output : \n"+work.getOutputString());
				try {
					writer_system = new BufferedWriter ( new FileWriter ( new File(config.getProperty("path")+"/Workload/Results/system_out.txt")));
					writer_system.write(work.getOutputString());
					writer_system.close();
				}
				catch ( IOException e) {
				}
			}
			if(work.getErrorProcess().length()!=0){		//erreur interne à l'exécutable dans "exe_error.txt"
				System.out.println("Executable error : \n"+ work.getErrorProcess());
				try {
					writer_error = new BufferedWriter ( new FileWriter (new File(config.getProperty("path")+"/Workload/Results/exe_error.txt")));
					writer_error.write(work.getErrorProcess());
					writer_error.close();
				}
				catch ( IOException e) {
				}
			}
			if(work.getExecException().length()!=0){  	//erreur du worker dans "worker_error.txt"
				System.out.println("Worker error : \n"+work.getExecException());
				try {
					writer_error = new BufferedWriter ( new FileWriter (new File(config.getProperty("path")+"/Workload/Results/worker_error.txt")));
					writer_error.write(work.getExecException());
					writer_error.close();
				}
				catch ( IOException e) {
				}
			}


		}
		

		System.out.println("work    delete="+DeleteDirectory(new File(config.getProperty("path")+"/Workload")));

		File workloadZip;
		if((workloadZip =new File(config.getProperty("path")+"/Workload.zip")).exists()){

			System.out.println("ZIP   delete="+workloadZip.delete());
		}

	}
	
	/**
	 * Recupère le path du répertoire contenant l'executable
	 * @return Retourne le chemin absolue du répertoire allant acceuillir le workload et les résultats
	 * @throws IOException
	 */
	
	private String getLocalStorageDirectory() throws IOException{


		Properties config = new Properties();
		FileInputStream in = new FileInputStream("Worker/config.properties");
		config.load(in);
		in.close();

		return config.getProperty("path");

	}
	
	/**
	 * Permet de créer la commande qui servira à exécuter la workload en fonction des paramètres d'exécution
	 * @return Retourne la commande d'exécution
	 */

	private String[] CmdBuilder (){

		String [] cmd = null;

		if(!workload.getExecParameters().getParameter().equals(null)){





			Object[] arr = (Object[])workload.getExecParameters().getParameter().toArray();
			int size = arr.length;
			cmd= new String[size];

			for(int i=0;i<size;i++){
				cmd[i]=(String) arr[i];
			}


		}



		return cmd;
	}

	public void setWorkload(WorkloadInformation workload){
		this.workload=workload;	
	}
	public WorkloadInformation getWorkload (){
		return workload;
	}
	public WorkloadExec getWork(){
		return work;
	}
	/**
	 * Cette classe permet de renvoyer au serveur les résultats lorsque l'exécution termine
	 * @param sock	Socket de connexion entre worker et server
	 * @throws IOException
	 */
	public void SendWhenFinish(Socket sock) throws IOException{

		ByteBuffer responceStatus = null;
		ByteBuffer buffer = null;

		
		Properties config = new Properties();
		FileInputStream in = new FileInputStream("Worker/config.properties");
		config.load(in);
		in.close();

		byte[] RES = new String("\0RES").getBytes();
		byte[] WORK_STATUS=ByteBuffer.allocate(4).putInt(13).array();
		byte[] WORK_COMPLETE=ByteBuffer.allocate(4).putInt(14).array(); 
		byte[] WORK_FAIL=ByteBuffer.allocate(4).putInt(15).array();
		byte[] ERROR=ByteBuffer.allocate(4).putInt(18).array();
		byte[] SIZE_NULL = ByteBuffer.allocate(4).putInt(0).array();


		if(this!=null){
			String getWorkloadStatus = this.getWork().getStatus();
			System.out.println(getWorkloadStatus);
			if (getWorkloadStatus!=null) {
				if (getWorkloadStatus.equalsIgnoreCase("fail")) {

					ZipFactory.Zipper(config.getProperty("path")+"/Workload/Results", config.getProperty("path")+"/ResultOut.zip");

					FileInputStream fis = new FileInputStream(new File(config.getProperty("path")+"/ResultOut.zip"));
					int zipRead;
					ArrayList<Integer> listByte= new ArrayList<Integer>();

					while((zipRead=fis.read())!=-1){
						listByte.add(zipRead);
					}
					int size = listByte.size();
					byte[]zipbyte = new byte[size];
					for(int i=0;i<size;i++){
						int a = listByte.get(i);
						zipbyte[i]= (byte)a;
					}

					byte[] sizeZip=ByteBuffer.allocate(4).putInt(size).array(); 

					responceStatus = ByteBuffer.allocate(RES.length+WORK_FAIL.length+sizeZip.length+size);

					responceStatus.put(RES);
					responceStatus.put(WORK_FAIL);
					responceStatus.put(sizeZip);
					responceStatus.put(zipbyte);
					buffer=responceStatus;
					fis.close();

				}
				if (getWorkloadStatus.equalsIgnoreCase("Running")) {

					responceStatus= ByteBuffer.allocate(RES.length+WORK_STATUS.length+SIZE_NULL.length);
					responceStatus.put(RES);
					responceStatus.put(WORK_STATUS);
					responceStatus.put(SIZE_NULL);
					buffer=responceStatus;


				}
				if (getWorkloadStatus.equalsIgnoreCase("endedOK")) {

					ZipFactory.Zipper(config.getProperty("path")+"/Workload/Results", config.getProperty("path")+"/ResultOut.zip");

					FileInputStream fis = new FileInputStream(new File(config.getProperty("path")+"/ResultOut.zip"));
					int zipRead;
					ArrayList<Integer> listByte= new ArrayList<Integer>();

					while((zipRead=fis.read())!=-1){
						listByte.add(zipRead);
					}

					int size = listByte.size();
					byte[]zipbyte = new byte[size];
					for(int i=0;i<size;i++){
						int a = listByte.get(i);
						zipbyte[i]= (byte)a;
					}

					byte[] sizeZip=ByteBuffer.allocate(4).putInt(size).array(); 

					responceStatus = ByteBuffer.allocate(RES.length+WORK_COMPLETE.length+sizeZip.length+size);

					responceStatus.put(RES);
					responceStatus.put(WORK_COMPLETE);
					responceStatus.put(sizeZip);
					responceStatus.put(zipbyte);
					buffer=responceStatus;
					fis.close();


				}
			}else{

				ZipFactory.Zipper(config.getProperty("path")+"/Workload/Results", config.getProperty("path")+"/ResultOut.zip");

				FileInputStream fis = new FileInputStream(new File(config.getProperty("path")+"/ResultOut.zip"));
				int zipRead;
				ArrayList<Integer> listByte= new ArrayList<Integer>();

				while((zipRead=fis.read())!=-1){
					listByte.add(zipRead);
				}
				int size = listByte.size();
				byte[]zipbyte = new byte[size];
				for(int i=0;i<size;i++){
					int a = listByte.get(i);
					zipbyte[i]= (byte)a;
				}

				byte[] sizeZip=ByteBuffer.allocate(4).putInt(size).array(); 

				responceStatus = ByteBuffer.allocate(RES.length+ERROR.length+sizeZip.length+size);


				responceStatus.put(RES);
				responceStatus.put(ERROR);
				responceStatus.put(sizeZip);
				responceStatus.put(zipbyte);
				buffer=responceStatus;
				fis.close();

			}
		}else{

			ZipFactory.Zipper(config.getProperty("path")+"/Workload/Results", config.getProperty("path")+"/ResultOut.zip");

			FileInputStream fis = new FileInputStream(new File(config.getProperty("path")+"/ResultOut.zip"));
			int zipRead;
			ArrayList<Integer> listByte= new ArrayList<Integer>();

			while((zipRead=fis.read())!=-1){
				listByte.add(zipRead);
			}
			int size = listByte.size();
			byte[]zipbyte = new byte[size];
			for(int i=0;i<size;i++){
				int a = listByte.get(i);
				zipbyte[i]= (byte)a;
			}

			byte[] sizeZip=ByteBuffer.allocate(4).putInt(size).array(); 

			responceStatus = ByteBuffer.allocate(RES.length+ERROR.length+sizeZip.length+size);


			responceStatus.put(RES);
			responceStatus.put(ERROR);
			responceStatus.put(SIZE_NULL);
			buffer=responceStatus;
			fis.close();

		}

		if(!(buffer==null)){
			System.out.println(buffer.capacity());
			if(buffer.array().length>11){

				OutputStream out = sock.getOutputStream();

				out.write(buffer.array());
				System.out.println("size send"+buffer.capacity());
				out.flush();
			}
		}



	}

	/**
	 * Supprime un répertoire contenant des fichiers
	 * @param path Chemin vers le répertoire
	 * @return Retourne true si le répertoire à été supprimé et false sinon
	 */
	public boolean DeleteDirectory(File path) {
		if( path.exists() ) {
			File[] files = path.listFiles();
			for(int i=0; i<files.length; i++) {
				if(files[i].isDirectory()) {
					DeleteDirectory(files[i]);
				}
				else {
					files[i].delete();
				}
			}
		}
		return( path.delete() );
	}



}
