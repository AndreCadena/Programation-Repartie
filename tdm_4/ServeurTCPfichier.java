package tcp;

import java.io.FileInputStream;
import java.io.IOException;
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
        // sur le port 5099
        ServerSocket socketEcoute = new ServerSocket();
        socketEcoute.bind(new InetSocketAddress(4001));


        // Attente de la connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();

        // Affichage du port et de l'ip du client 
        System.out.println("Un client est connecté");
        System.out.println("IP:"+socketConnexion.getInetAddress());
        System.out.println("Port:"+socketConnexion.getPort());

        FileInputStream fis = new FileInputStream("/home/userir/file_serveur.txt");
        byte[] buf = new byte[10000];

        int len = fis.read(buf);
        while(len!=-1)
        {
        	OutputStream os = socketConnexion.getOutputStream();
            os.write(buf);
            len = fis.read(buf);
        }
        fis.close();


        // Fermeture de la socket de connexion
        socketConnexion.close();


        // Arret du serveur 
        socketEcoute.close();
        System.out.println("Arret du serveur .");
    }

}
