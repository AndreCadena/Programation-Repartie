package fr.esisar;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

public class P2 {

	public static void main(String[] args) throws Exception
    {
        P2 c = new P2();
        c.execute();
    }
	
	private void execute() throws Exception
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
