package tdm5;

import java.util.Random;

public class sequencement extends Thread {
	public static void main(String[] args) throws Exception
    {
		sequencement t1 = new sequencement();
		sequencement t2 = new sequencement();
		sequencement t3 = new sequencement();
		sequencement t4 = new sequencement();
		sequencement t5 = new sequencement();
		sequencement t6 = new sequencement();

		t1.start();
		t1.join();
		t2.start();
		t3.start();
		t4.start();
		t2.join();
		t3.join();
		t5.start();
		t5.join();
		t4.join();
		t6.start();
		t6.join();
		
    }
	
	public void run() {
		Random r = new Random();
		int low = 500;
		int high = 10000;
		int result = r.nextInt(high-low) + low;
		System.out.println("Sleep for: " + result);
		try {
			Thread.sleep(result);
			System.out.println("Done");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	
	}
}
