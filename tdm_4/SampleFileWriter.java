package tdm4;
import java.io.FileOutputStream;
import java.io.IOException;


public class SampleFileWriter 
{
    public static void main(String[] args) throws Exception
    {
        SampleFileWriter fr = new SampleFileWriter();
        fr.execute();
    }


    /**
     * 
     */
    private void execute() throws IOException
    {
        System.out.println("Début écriture du fichier");

        FileOutputStream fos = new FileOutputStream("/home/userir/lecture_fichier.txt");


        byte[] buf = new byte[10];

        buf[0] = 11;
        buf[1] = 10;
        buf[2] = 45;
        buf[3] = 44;
        buf[4] = 15;
        buf[5] = 99;
        buf[6] = 99;


        // Ecriture des 6 premiers octets du buffer 
        fos.write(buf,0,7);

        // Fermeture du fichier
        fos.close();

        System.out.println("Fin écriture du fichier");
    }
}
