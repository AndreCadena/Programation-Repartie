package tcp;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Client basique TCP
 * 
 */
public class Clientadition
{

    public static void main(String[] args) throws Exception
    {
        Clientadition clientTCP = new Clientadition();
        clientTCP.execute();                
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
        socket.connect(adrDest);        

        
        // Attente de la reponse 
        int lenBufR = 0;
        int combnum = 0;
        int num1 =0;
        int num2 =0;
        while(lenBufR != -1) {
        	
        	byte[] bufR = new byte[2048];
        	InputStream is = socket.getInputStream();
        	lenBufR = is.read(bufR);
        	if (lenBufR!=-1)
        	{
        		String reponse = new String(bufR, 0 , lenBufR );
        		for (int i = 0; i< reponse.length(); i++) {
            		String bufC = reponse.substring(i,i+1);
            		if(bufC.startsWith("+") ) {
            			String bufS = reponse.substring(i-combnum, i);
            			num1 = Integer.parseInt(bufS);
            			combnum = 0;
            			continue;
            		}
            		else if(bufC.startsWith("?")) {
            			String bufS = reponse.substring(i-combnum, i);
            			num2 = Integer.parseInt(bufS);
            			combnum = 0;
            			
            			int numr = num1 + num2;
            			byte[] bufE = new String(numr + ";").getBytes();
            	        OutputStream os = socket.getOutputStream();
            	        os.write(bufE);
            		}
            		else {
            			
            			combnum++;
            		}
            	}
            
        	}
        	
        	
        }
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}
