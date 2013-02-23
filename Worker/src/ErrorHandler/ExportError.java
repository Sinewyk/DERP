package ErrorHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
/**
 * 
 * @author Ostro
 *
 */
public class ExportError {
	/**
	 * Permet d'exporter l'execption conduisant le worker à une erreur
	 * @param e Exception rencontrée
	 */
	public static void ExportErrorToFile(Exception e){
		
		try {
			
			Properties config = new Properties();
			FileInputStream in = new FileInputStream("Worker/config.properties");
			config.load(in);
			in.close();
			PrintWriter pw =new PrintWriter(new FileWriter(new File(config.getProperty("path")+"/log.txt"), true));
			
			String format = "dd/MM/yy H:mm:ss"; 

			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
			java.util.Date date = new java.util.Date(); 
			
			pw.print("\n"+ formater.format(date)+"\n");
			e.printStackTrace(pw);
			
			pw.close();
			
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
			//ExportErrorToFile(e1);
			
		}
	
		
	}

}
