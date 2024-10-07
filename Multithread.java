package tdm5;

import java.util.ArrayList;

public class Multithread extends Thread {
	public long start;
	public long end;
	public float res;
	
	
	public static void main(String[] args) throws Exception
    {
		long start = System.currentTimeMillis();
		
		int num_threads = 2;
		ArrayList <Multithread> threads = new ArrayList <Multithread>(num_threads);
		for(int i = 0; i< num_threads; i++) {
			long start1 = 1 + i*(2000000000/num_threads);
			long end1 = (i+1)*(2000000000/num_threads);
			Multithread t = new Multithread(start1,end1);
			threads.add(t);
		}
		
		for(int i = 0; i< num_threads; i++) {
			threads.get(i).start();
		}
        
		for(int i = 0; i< num_threads; i++) {
			threads.get(i).join();
		}
		float pi_6 = 0;
		for(int i = 0; i< num_threads; i++) {
			pi_6 = pi_6 + threads.get(i).getres();
		}
		
        System.out.println("PI^1/6= "+pi_6);
        long stop = System.currentTimeMillis();
        System.out.println("Elapsed Time = "+(stop-start)+" ms");

    }
	
	public Multithread(long start,long end)
    {
        this.start = start;
        this.end = end;
    }
	
	public void run() {
		
	float pi_6 = (float) 0.0;
		for(long i = this.start; i< end+1; i++) {
			pi_6 = (float) (pi_6 + ((1/(Math.pow(i, 2)))));
		}
	this.res = pi_6;
	}
	
	public float getres() {
		return res;
		
	}
	
}
