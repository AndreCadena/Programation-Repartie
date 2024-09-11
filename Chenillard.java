package fr.esisar;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

public class Chenillard {
	
	public int portSelf;
	public int portdest;
	
	
	
	public static void main(String[] args) throws Exception
    {
        Chenillard c1 = new Chenillard();
        c1.execute1();
        c2.execute2();
        c3.execute2();
        c4.execute2();
        
    }
	
	private void execute1() throws Exception
    {
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4002));
        System.out.println("Demarrage du serveur");
        
		JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
   
        
		while(true) {
			
	        byte[] bufR = new byte[2048];
	        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
	        socket.receive(dpR);
	        String message = new String(bufR, dpR.getOffset(), dpR.getLength());
	 
	        if(message.startsWith("red")) {
	        	frame.getContentPane().setBackground(Color.RED);
		        frame.setVisible(true);
		        Thread.sleep(1000);
		        frame.getContentPane().setBackground(Color.GREEN);
		        frame.setVisible(true);
		        InetSocketAddress adrDest = new InetSocketAddress("192.168.130.163", 4001);
		        byte[] bufE = new String("red").getBytes();
		        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		        socket.send(dpE);
		        
	        }
	        else {
	        	break;
	        
		}
    }

}
}
