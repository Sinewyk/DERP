package BringAllTheRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.bind.JAXBException;

/**
 * 
 * @author Ostro
 *	Cette classe gère la lecture et l'écriture via socket entre le server et le worker.
 */
public class ListeningSocket {


	Socket socket;

	/**
	 * debut de la connexion avec le serveur
	 * envoie l'autthentification au serveur et attend la réponse d'authenthification de celui-ci 0=ok 1=non ok
	 * Début de l'écoute du stream de la socket worker serveur node.js
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public ListeningSocket () throws UnknownHostException, IOException, JAXBException{



			Properties config = new Properties();
			FileInputStream in = new FileInputStream("Worker/config.properties");
			config.load(in);
			in.close();
	
			
			//this.socket = new Socket("localhost", 6789);
			this.socket = new Socket(config.getProperty("ip"), Integer.parseInt(config.getProperty("port")));
			socket.setKeepAlive(true);
			
			//authentification au pres du server
			byte[] WORKER_CONNEC_REQ=ByteBuffer.allocate(4).putInt(29).array();			
			byte[] REQ = new String("\0REQ").getBytes();
			
			
			byte[] mdp  = new String("logon").getBytes();
			int size_transmit = mdp.length;
			
			byte[] header_size= ByteBuffer.allocate(4).putInt(size_transmit).array();
			
			ByteBuffer buff_authen = ByteBuffer.allocate(WORKER_CONNEC_REQ.length+REQ.length+header_size.length+size_transmit);
			buff_authen.put(REQ);
			buff_authen.put(WORKER_CONNEC_REQ);
			buff_authen.put(header_size);
			buff_authen.put(mdp);
			
			
			
			
		
			
			Send(buff_authen);

			RequestRouter rq = new RequestRouter();
			
				System.out.println("try ecoute");
				byte[] buff_authen_res = Listen();
				
				byte[] res = new byte[buff_authen_res.length-12];
				for(int i=12;i<buff_authen_res.length;i++){
					
					res[i-12]=buff_authen_res[i];
					
				}
				
				if(res[0]==0){
					System.out.println("Authentification ok");
					new Thread(new DeamonClosure(socket)).start();
					
					

					while(!socket.isClosed()){


						if(socket.isConnected()){
							
							System.out.println("debut ecoute");
							ByteBuffer buff = rq.RespondToRequest(Listen(),socket);

							System.out.println("debut ecriture");
							
							Send(buff);


						}


					}



					System.out.println("fin");


				





					 
				}else{
					System.out.println("Authentification non ok");
				}
				
				
				

			

			

	}
	/**
	 * Permet la lecture des information envoyer a travers le socket worker/server
	 * 
	 * @return Retourne un array de byte de ce que le serveur a envoyer
	 * @throws IOException
	 */
	public byte[] Listen() throws IOException{

		InputStream	in= this.socket.getInputStream();
		ArrayList<Integer> received = new ArrayList<Integer>();
		
		int byteRead=0;
		int ii =0;
		int si=0;
		boolean end = false ;
		//System.out.println("Start listen    ");
		while(!end){
			byteRead=in.read();
			//System.out.println("pos  "+(ii+1)+"   "+byteRead);
			received.add(byteRead);
			
			if(ii==11){
				
				byte[] sizearray = new byte[4];
				sizearray[0]=received.get(8).byteValue();
				sizearray[1]=received.get(9).byteValue();
				sizearray[2]=received.get(10).byteValue();
				sizearray[3]=received.get(11).byteValue();

				si= new BigInteger(sizearray).intValue();
				//System.out.println("Size = "+si);
				


			}

			if(ii==(si +11) ){

				end=true;

			}

			ii++;


		}
		//System.out.println("End listen");

		int size = received.size();
		byte[]arraybyte = new byte[size];
		for(int i=0;i<size;i++){
			int a = received.get(i);
			arraybyte[i]= (byte)a;
		}
		
		//in.close();
		
		return arraybyte;
	}
	/**
	 * Permet l'envoie vers le server d'un ByteBuffer
	 * 
	 * @param bytearray message a envoyer au server
	 * @throws IOException
	 */
	
	public void Send (ByteBuffer bytearray) throws IOException{
		
		
		
		if(!(bytearray==null)){
			if(bytearray.array().length>11){
				
				OutputStream out = socket.getOutputStream();
				out.write(bytearray.array());
				out.flush();
			}
		}
		//out.close();



	}

}


