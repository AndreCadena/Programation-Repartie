package tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur basique TCP
 */
public class ServeurTCPfichier
{

    public static void main(String[] args) throws Exception
    {
        ServeurTCPfichier serveurTCP = new ServeurTCPfichier();
        serveurTCP.execute();

    }

    

    private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        ServerSocket socketEcoute = new ServerSocket();
        socketEcoute.bind(new InetSocketAddress(4001));


        // Attente de la connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();

        // Affichage du port et de l'ip du client 
        InputStream is = socketConnexion.getInputStream();
		OutputStream os = socketConnexion.getOutputStream();
		
		String filename = readFileName(is);
        FileInputStream fis = new FileInputStream(filename);
       
        
        File f = new File(filename);
        long fileSize = f.length();
        
        byte[] bufE = new String("Taille du fichier= " + fileSize + " octets").getBytes();
        os.write(bufE);
        
        
        byte[] buf = new byte[10*1024];

        int len = fis.read(buf);
        while(len!=-1)
        {
            os.write(buf,0,len);
            len = fis.read(buf);
        }
        fis.close();


        // Fermeture de la socket de connexion
        socketConnexion.close();


        // Arret du serveur 
        socketEcoute.close();
        System.out.println("Arret du serveur .");
    }
   
    private String readFileName(InputStream is) throws IOException
	{
		
		String fileName ="";
		byte[] buf = new byte[2048];
		
		while(fileName.endsWith("!")==false)
		{
			int nbRead = is.read(buf);
			String str = new String(buf,0,nbRead);
			fileName = fileName+str;
		}
		
		// On enleve ensuite le ! 
		fileName = fileName.substring(0, fileName.length()-1);
		return fileName;
	}

}
