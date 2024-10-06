package tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Client basique TCP
 * 
 */
public class ClientTCPfichier
{

    public static void main(String[] args) throws Exception
    {
        ClientTCPfichier clientTCP = new ClientTCPfichier();
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
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4001);
        socket.connect(adrDest);        
        String filename = "D:\\Eclipse HD\\ESISAR\\eclipse-workspace\\tdm_4\\test1.txt";
        OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();
		
        byte[] bufE = new String(filename+"!").getBytes();
		os.write(bufE);
		System.out.println("Nom du fichier envoyé");

		FileOutputStream fos = new FileOutputStream(filename+"-copie.txt");
		byte[] bufR = new byte[2048];
		int lenBufR = is.read(bufR);
        if (lenBufR!=-1)
        {
            String message = new String(bufR, 0 , lenBufR);
            System.out.println(message);
        }
		
        byte[] buf = new byte[10*1024];

        // Attente de la reponse 
        lenBufR = is.read(buf);
        while (lenBufR!=-1)
        { 
        	fos.write(buf,0,lenBufR);
        	lenBufR = is.read(buf);
        }
        fos.close();
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}
