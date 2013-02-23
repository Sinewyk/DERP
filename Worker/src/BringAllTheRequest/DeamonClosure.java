package BringAllTheRequest;

import java.math.BigInteger;
import java.net.Socket;
import java.nio.ByteBuffer;
/**
 * 
 * @author Ostro
 *	Cette classe permet la création d'un Deamon qui supervise le worker.
 *	Cette classe est particulièrement utile si l'on veux débugger le worker car elle permet
 *	s'assigner des evenements a des touches afin de simuler un cas d'utilisation.
 *	
 *	Le Deamon lis les inputs System du clavier.
 *	
 *	Option définie : 
 *	"q"=fermer le socket
 *	"a"=envoie d'un message au server
 */

public class DeamonClosure implements Runnable{


	private Socket sock;

	public DeamonClosure (Socket sock){

		this.sock=sock;




	}


	public void run() {

		
		try{
		int action = 259;
		while(!(action == new BigInteger("q".getBytes()).intValue())){
				
				//System.out.println(action +"       action");
				action= System.in.read();
				

				switch(action){

				case 97 : 
					byte[] REQ = new String("\0REQ").getBytes();
					byte[] ERROR=ByteBuffer.allocate(4).putInt(16).array();
					byte[] SIZE_NULL = ByteBuffer.allocate(4).putInt(0).array();

					ByteBuffer buffer = null;
					ByteBuffer responceStatus = ByteBuffer.allocate(12);

					responceStatus.put(REQ);
					responceStatus.put(ERROR);
					responceStatus.put(SIZE_NULL);

					buffer=responceStatus;
					sock.getOutputStream().write(buffer.array());
					break;

				}


		


		}

		
			getSocket().close();
		}catch (Exception e){
			
		}


	}

	public Socket getSocket(){
		return sock;
	}



}
