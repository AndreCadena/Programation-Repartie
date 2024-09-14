package tdm_2;

import java.awt.Color;
import java.awt.HeadlessException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.io.IOException;

import javax.swing.JFrame;

public class Chenillard {

	
	public static void main(String[] args) throws Exception
    {
        Chenillard c1 = new Chenillard();
        c1.execute1(args[0], args[1]);
        
        
    }
	
	private void execute1(String arg1, String arg2) throws IOException, InterruptedException
    {
		
		int port1 = Integer.parseInt(arg1);
		int port2 = Integer.parseInt(arg2);
		
		try (DatagramSocket socket = new DatagramSocket(null)) {
			socket.bind(new InetSocketAddress(port1));
			
			JFrame frame = new JFrame("Chenillard");
			frame.setSize(300,300);
			frame.getContentPane().setBackground(Color.GREEN);
			frame.setVisible(true);
			frame.getContentPane().setBackground(Color.RED);
			frame.setVisible(true);
			Thread.sleep(1000);
			frame.getContentPane().setBackground(Color.GREEN);
			frame.setVisible(true);
   
			InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", port2);
			byte[] bufE = new String("red").getBytes();
			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
			socket.send(dpE);
			
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
			        InetSocketAddress adrDest2 = new InetSocketAddress("127.0.0.1", port2);
			        byte[] bufE2 = new String("red").getBytes();
			        DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, adrDest2);
			        socket.send(dpE2);
			        
			    }
			    
			    
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

