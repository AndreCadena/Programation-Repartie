package tdm_2;

import java.awt.HeadlessException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.util.LinkedList;

public class ServeurChanillard {
	
	public static void main(String[] args) throws Exception
    {
        ServeurChanillard c1 = new ServeurChanillard();
        c1.execute1(args[0]);
        
        
    }
	
	private void execute1(String arg1) throws IOException, InterruptedException
    {
		
		int port1 = Integer.parseInt(arg1);
		int ans = 0;
		int lastClient = 0;
		try (DatagramSocket socket = new DatagramSocket(null)) {
			socket.bind(new InetSocketAddress(port1));
			System.out.println("Demarrage du serveur");
			
			LinkedList <Integer> clients = new LinkedList<Integer>();
			
			while(true) {
				
			    byte[] bufR1 = new byte[2048];
			    DatagramPacket dpR1 = new DatagramPacket(bufR1, bufR1.length);
			    socket.receive(dpR1);
			    String message = new String(bufR1, dpR1.getOffset(), dpR1.getLength());
 
			    if(message.startsWith("new")) {
				    
			        clients.addLast(dpR1.getPort());
			        
			    }

			    else if(message.startsWith("last")) {
				    
			        clients.addLast(dpR1.getPort());
			        lastClient = dpR1.getPort();
			        
			        InetSocketAddress adrDest3 = new InetSocketAddress("127.0.0.1", clients.getFirst());
			        byte[] bufE3 = new String("red").getBytes();
			        DatagramPacket dpE3 = new DatagramPacket(bufE3, bufE3.length, adrDest3);
			        socket.send(dpE3);
			        
			    }
			    
			    else if(message.startsWith("red")) {
			    
			    	if(dpR1.getPort() == lastClient) {
			    		InetSocketAddress adrDest1 = new InetSocketAddress("127.0.0.1", clients.getFirst());
				        byte[] bufE1 = new String("red").getBytes();
				        DatagramPacket dpE1 = new DatagramPacket(bufE1, bufE1.length, adrDest1);
				        socket.send(dpE1);
			    	}
			    	else {
			    		for(int i = 0; i < clients.size(); i++) {
			    			if(dpR1.getPort() == clients.get(i)) {
			    				ans = i+1;
			    			}
			    		}
			    
			        InetSocketAddress adrDest2 = new InetSocketAddress("127.0.0.1", clients.get(ans));
			        byte[] bufE2 = new String("red").getBytes();
			        DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, adrDest2);
			        socket.send(dpE2);
			    	}
			    }
			    
			    
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

