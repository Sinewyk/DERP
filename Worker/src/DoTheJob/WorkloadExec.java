package DoTheJob;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


import ErrorHandler.ExportError;
import JAXBManager.WorkloadInformation;


/**
 * Cette classe exécute la workload en fonction des paramètres du WorkloadManager.
 * @author Ostro
 *
 */

public class WorkloadExec implements Runnable{

	
	private String[] execCmd;
	private WorkloadInformation workloadInformation;
	private boolean isExecOk=true;
	private String path;
	private String execException ="";	// erreur du worker;
	private String outputString ="";		// output de l'exï¿½cutable;	
	private String errorProcess ="" ; 		// erreurs interne ï¿½ l'exï¿½cutable;
	private String status=null;
	
	
	/**
	 * Constructeur surchargé.
	 * @param execCmd	la ligne de commande a exécuter
	 * @param workload	information concernant les paramètres déexécution.
	 */
	public WorkloadExec (String[] execCmd, WorkloadInformation workload){
		
		this.execCmd=execCmd;
		this.workloadInformation= workload;
		
	}
	/**
	 * Modifie un boolean indiquant s'il y eu une erreur lors de l'exécution
	 */
	private void ExecErrorHappend(){
		
		setExecOk(false);
		
	}
	
	/**
	 * Lance l'exécution de la workload
	 */
	public void run() {
		System.out.println("debut du workload");
		try {
			status="Running";
			Runtime run = Runtime.getRuntime();		
			Process workload = ExecAsRightType(workloadInformation, run);
			
			
			// <----------  Pour lire les system.out  ---------->
			 
				InputStream in = workload.getInputStream();
				StringBuilder build = new StringBuilder(); 
				char c = (char) in.read(); 
	
				while (c != (char) -1) { 
				build.append(c); 
				c = (char) in.read(); 
				} 
	
				String response = build.toString(); 
				outputString+=response;
			//	---------------------------------------------------		
				
			
			
			

			
		
			
			// <----------  Pour lire les erreurs  ---------->
			 
				
				InputStream error = workload.getErrorStream();
				StringBuilder buildError = new StringBuilder(); 
				char cerror = (char) error.read(); 
	
				while (cerror != (char) -1) { 
				buildError.append(cerror); 
				cerror = (char) error.read(); 
				} 
	
				String responseError = buildError.toString(); 
				errorProcess+=responseError;
		//---------------------------------------------------		
		
			workload.waitFor();
			int returnValue = workload.exitValue();
			System.out.println(returnValue);
			status="EndedOK";
			if(errorProcess.length()!=0){
				ExecErrorHappend();
				status="fail";
			}
			
			System.out.println("fin du workload");
			
		}catch (Exception  e) {
		
			e.printStackTrace();
			ExecErrorHappend();
			setExecException(e);
			status="fail";
			ExportError.ExportErrorToFile(e);
		}
		
	}
	public boolean isExecOk() {
		return isExecOk;
	}
	public void setExecOk(boolean isExecOk) {
		this.isExecOk = isExecOk;
	}
	public String getExecException() {
		return execException;
	}
	public void setExecException(Exception execException) {
		this.execException = execException.getMessage();
	}
	public String getOutputString (){
		return outputString;
	}
	public String getErrorProcess(){
		return errorProcess;
	}
	public void setPath(String path){
		this.path=path;
	}
	public String getStatus (){
		return status;
	}
	/**
	 * Lance l'exécution de la workload en fonction des paramètres de configuration et du Système d'exploitation présant
	 * @param workload parametre d'exécution du workload
	 * @param run	Runtime du système
	 * @return Retourne le Process créer par l'appel de la fonction Exec
	 * @throws IOException
	 */
	public Process ExecAsRightType(WorkloadInformation workload, Runtime run) throws IOException{
		
		int type = workload.getExecType().intValue();
		Process process = null;
		switch(type){
		
		//java type
		case 1 : 
			for(int i=0;i<execCmd.length;i++){
				System.out.println(execCmd[i]);
			}
			process = run.exec(execCmd, null, new File(path+"/Results"));
			break;
		
		//EXE type
		case 2 : 
			
			String os =System.getProperty("os.name");
			
			if(os.startsWith("Windows")){
				File exe = new File(path+execCmd[0]);
				exe.setExecutable(true);
				exe.setReadable(true);
				exe.setWritable(true);
				String cmd = path+"/";
				for(int i=0;i<execCmd.length;i++){
				cmd+=execCmd[i]+" ";
				}
				System.out.println(cmd);
				process = run.exec(cmd, null, new File(path+"/Results"));
			}

			
			if(os.startsWith("Linux")||os.startsWith("Mac")){

				File exe = new File(path+"/"+execCmd[0]);
				
				exe.setExecutable(true);
				exe.setReadable(true);
				exe.setWritable(true);
				String cmd = path+"/";
				for(int i=0;i<execCmd.length;i++){
				cmd+=execCmd[i]+" ";
				}
				System.out.println(cmd);
				process = run.exec(cmd, null, new File(path+"/Results"));
				
				
			}

			
			
			break;
		
		default : 
			break;
			
		}
		return process;
		
		
		
	}

}
