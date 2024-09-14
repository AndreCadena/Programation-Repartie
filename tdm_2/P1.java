package fr.esisar;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

public class P1 {

	public static void main(String[] args) throws Exception
    {
        P1 c = new P1();
        c.execute();
    }
	
	private void execute() throws Exception
    {
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4001));
        System.out.println("Demarrage du serveur");
        
		JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.RED);
        frame.setVisible(true);
        Thread.sleep(1000);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        
        InetSocketAddress adrDest1 = new InetSocketAddress("127.0.0.1", 4002);
        byte[] bufE1 = new String("red").getBytes();
        DatagramPacket dpE1 = new DatagramPacket(bufE1, bufE1.length, adrDest1);
        socket.send(dpE1);
   
        
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
		        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4002);
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
