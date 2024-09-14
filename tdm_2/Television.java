package fr.esisar;

import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Television {
	
	public static void main(String[] args) throws Exception
    {
        Television c = new Television();
        c.execute();
    }
	
	private void execute() throws Exception
    {
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(7052));
        System.out.println("Demarrage du serveur");
        
		JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setVisible(true);
   
        
		while(true) {
	        byte[] bufR = new byte[2048];
	        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
	        socket.receive(dpR);
	        String message = new String(bufR, dpR.getOffset(), dpR.getLength());
	 
	        if(message.startsWith("red")) {
	        	frame.getContentPane().setBackground(Color.RED);
		        frame.setVisible(true);
	        }
	        else if(message.startsWith("green")){
	        	frame.getContentPane().setBackground(Color.GREEN);
		        frame.setVisible(true);
	        }
	        else if(message.startsWith("end")) {
	        	break;
	        }
	        else {
	        	byte[] bufE = new String("Envoyez une couleur valide s'il vous plaît").getBytes();
	            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, 
	                    dpR.getAddress(),dpR.getPort());
	            socket.send(dpE);
	        }
	        
		}
		
		frame.dispose();
    	socket.close();
        System.out.println("Arret du serveur .");
    }

}
