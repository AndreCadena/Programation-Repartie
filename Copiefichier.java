package tdm4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copiefichier {
	public static void main(String[] args) throws Exception
	{
		Copiefichier fr = new Copiefichier();
		fr.execute();
	}
		
	private void execute() throws IOException
	{
		long start = System.currentTimeMillis();
		System.out.println("Début copie du fichier");
	    FileInputStream fis = new FileInputStream("/home/userir/file1.txt");
	    byte[] buf = new byte[1000000];
	    FileOutputStream fos = new FileOutputStream("/home/userir/lecture_fichier1.txt");
	    

	    int len = fis.read(buf);
	    while(len!=-1)
	    {
	    	fos.write(buf,0,len);
	        len = fis.read(buf);
	    }
	    fis.close();
	    fos.close();
	    System.out.println("Fin copie du fichier");
	    long stop = System.currentTimeMillis();
	    System.out.println("Elapsed Time = "+(stop-start)+" ms");
	 }
}
