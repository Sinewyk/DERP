import BringAllTheRequest.ListeningSocket;

import ErrorHandler.*;

public class Main {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {



		try {
			ListeningSocket sock = new ListeningSocket();
			

		}catch (Exception e) {
			
			
			System.out.println("debut export");
			ExportError.ExportErrorToFile(e);

			
		}
		
		


	}

}
