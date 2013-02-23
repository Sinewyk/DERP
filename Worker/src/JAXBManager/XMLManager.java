package JAXBManager;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
/**
 * 
 * @author Ostro
 *
 */
public class XMLManager {
	
	private WorkloadInformation workload;
	/**
	 * Permet de convertir un fichier XML en une instance de WorkloadInformation
	 * @param XMLPath Chemin vers le fichier XML à convertir.
	 * @throws JAXBException
	 */
	public XMLManager ( String XMLPath ) throws JAXBException{
		
		JAXBContext jc = JAXBContext.newInstance(WorkloadInformation.class);
		Unmarshaller um = jc.createUnmarshaller();
		workload=(WorkloadInformation) um.unmarshal(new File(XMLPath));
		
	}
	
	public WorkloadInformation getWorkload (){
		
		return workload;
	}

}
