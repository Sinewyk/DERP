package BringAllTheRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.bind.JAXBException;


import DoTheJob.WorkloadBuilder;
import DoTheJob.WorkloadManager;
/**
 * 
 * @author Ostro
 *	Cette classe défini une methode permettant le routing des requetes et des reponses venant et à destination du server
 */
public class RequestRouter {

	WorkloadManager manager;

	
	
	/**
	 * Sert de routing des requetes et des reponses du server.
	 * @param bytearray message reçu
	 * @param sock	la socket de connexion entre le worker et le server
	 * @return	Retourne un ByteBuffer comprenant un message a envoyer au server. Peut être null
	 * @throws IOException
	 * @throws JAXBException
	 */
	public ByteBuffer RespondToRequest (byte[] bytearray ,Socket sock) throws IOException, JAXBException{


		Properties config = new Properties();
		FileInputStream in = new FileInputStream("Worker/config.properties");
		config.load(in);
		in.close();
		byte[] WORKLOAD_RECEIVED=ByteBuffer.allocate(4).putInt(9).array();
		byte[] WORK_STATUS=ByteBuffer.allocate(4).putInt(13).array();
		byte[] WORK_COMPLETE=ByteBuffer.allocate(4).putInt(14).array(); 
		byte[] WORK_FAIL=ByteBuffer.allocate(4).putInt(15).array();
		byte[] ECHO_RES=ByteBuffer.allocate(4).putInt(17).array();
		byte[] ERROR=ByteBuffer.allocate(4).putInt(18).array();
		byte[] WORKER_SPECS_RES = ByteBuffer.allocate(4).putInt(20).array();

		byte[] RES = new String("\0RES").getBytes();

		byte[] SIZE_NULL = ByteBuffer.allocate(4).putInt(0).array();


		ByteBuffer buffer = null;

		if(!bytearray.equals(null)){
			if(bytearray.length>11){



				byte[] readType = new byte[4];
				byte[] readAction = new byte[4];
				byte[] readSize = new byte[4];

				for (int i=0;i<4;i++){

					readType[i]=bytearray[i];
					readAction[i]=bytearray[i+4];
					readSize[i]=bytearray[i+8];

				}
				int typeValue= new BigInteger(readType).intValue();
				int actionValue= new BigInteger(readAction).intValue();


				switch(typeValue){

				// req_value= 5391697; 
				case  5391697  :


					switch(actionValue){

					//SUBMIT_WORKLOAD
					case 8 :
						System.out.println("requete 8");
						FileOutputStream fos = new FileOutputStream(new File (config.getProperty("path")+"/Workload.zip"));
						int lenght= new BigInteger(readSize).intValue();


						byte[] ziparray = new byte[lenght];
						for(int i =0 ; i <lenght;i++){

							ziparray[i]=bytearray[i+12];
						}
						fos.write(ziparray);

						ByteBuffer responce = ByteBuffer.allocate(12);
						responce.put(RES);
						responce.put(WORKLOAD_RECEIVED);
						responce.put(SIZE_NULL);
						buffer=responce;
						
						fos.flush();
						fos.close();

						System.out.println("debut workload");
						manager = WorkloadBuilder.BuildWorkload(sock);
						

						break;

						//WORK_STATUS_IS
					case 12 : 
						
						System.out.println("requete 12");
						ByteBuffer responceStatus = null;
								

						if(manager!=null){
							String getWorkloadStatus = this.manager.getWork().getStatus();
							System.out.println(getWorkloadStatus);
							if (getWorkloadStatus!=null) {
								if (getWorkloadStatus.equalsIgnoreCase("fail")) {

									
									
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


						break;
					//WORKER_SPECS_REQ
					case 19 : System.out.println("requete 19");

					
					InetAddress thisIp =InetAddress.getLocalHost();
					String ipAddress = thisIp.getHostAddress();
					
					OperatingSystemMXBean opBean = ManagementFactory.getOperatingSystemMXBean();
					int nbCore = opBean.getAvailableProcessors();
					
					String OS = System.getProperty("os.name");
										
					String model  =System.getProperty("sun.arch.data.model") ;
					
					String hostname = thisIp.getHostName();
					
					String ram =""+ Runtime.getRuntime().totalMemory();
					
					String jSON ="{\"ipAddress\":\""+ipAddress+"\",\"nbOfCore\":\""+nbCore+"\",\"os\":\""+OS+"\",\"archi\":\""+model+"\",\"hostname\":\""+hostname+"\",\"RAM\":\""+ram+"\"}";
					
					byte[] specs = jSON.getBytes();
					byte[] sizeSpecs = ByteBuffer.allocate(4).putInt(specs.length).array();
					
					ByteBuffer workerSpecsBuff = ByteBuffer.allocate(RES.length+WORKER_SPECS_RES.length+sizeSpecs.length+specs.length);
					workerSpecsBuff.put(RES);
					workerSpecsBuff.put(WORKER_SPECS_RES);
					workerSpecsBuff.put(sizeSpecs);
					workerSpecsBuff.put(specs);
					buffer=workerSpecsBuff;
					
						break;
					// REQ_ECHOES
					case 16 : 
						System.out.println("requete 16 reçu");
						break;

					default : System.out.println("requeste pas codée");

					ByteBuffer responceUnknow = ByteBuffer.allocate(12);
					responceUnknow.put(RES);
					responceUnknow.put(ERROR);
					responceUnknow.put(SIZE_NULL);
					buffer=responceUnknow;

					break;


					}




					break;

					// res_value = 5391699;
				case 5391699   :

					switch(actionValue){

					//ECHO_RES					
					case 17 : System.out.println("Echoes back received");
					ByteBuffer responceUnknow = ByteBuffer.allocate(12);
					responceUnknow.put(RES);
					responceUnknow.put(ECHO_RES);
					responceUnknow.put(SIZE_NULL);
					buffer=responceUnknow;

					break;



					default : System.out.println("requeste inconnu");

							 

						break;
					}
					break;

				}


			}
		}



		return buffer;		
	}

}
