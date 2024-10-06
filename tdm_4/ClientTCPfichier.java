package tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        FileOutputStream fos = new FileOutputStream("/home/userir/file_client.txt");
        byte[] buf = new byte[10000];

        // Attente de la reponse 
        InputStream is = socket.getInputStream();
        int lenBufR = is.read(buf);
        while (lenBufR!=-1)
        { 
        	fos.write(buf,0,10000);
        }
        fos.close();
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}
