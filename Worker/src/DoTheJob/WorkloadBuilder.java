package DoTheJob;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import ZipFactoryPackage.ZipFactory;

import JAXBManager.XMLManager;
/**
 * 
 * @author Ostro
 *
 */
public class WorkloadBuilder {

	/**
	 * Cette classe sert à la préparation du workload reçu par le worker afin de débuter l'exécution de la workload
	 * @param socket	le socket de connexion entre le worker et le server
	 * @return	Retourne une instance de WorkloadManager qui supervise l'exécution du workload
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static WorkloadManager BuildWorkload(Socket socket) throws IOException, JAXBException{

		String pathDir ="Workload";
		String nameXML="config.xml";
		Properties config = new Properties();
		FileInputStream in = new FileInputStream("Worker/config.properties");
		config.load(in);
		in.close();
		
		WorkloadManager manager = new WorkloadManager(socket);
		
		
			ZipFactory.Unzipper(config.getProperty("path")+"/Workload.zip");
			
			XMLManager man = new XMLManager(config.getProperty("path")+"/"+pathDir+"/"+nameXML);
			manager.setWorkload(man.getWorkload()); 
			new File(config.getProperty("path")+"/Workload/Results").mkdir();
			new Thread  (manager).start();


		
		return manager;

	}
}
