package tdm5;

public class monothread {
	public static void main(String[] args) throws Exception
    {
        monothread calcul = new monothread();
        calcul.execute();                
    }
	
	public void execute() {
		long start = System.currentTimeMillis();
	float pi_6 = (float) 0.0;
		for(int i = 1; i< 2000000000+1; i++) {
			pi_6 = (float) (pi_6 + ((1/(Math.pow(i, 2)))));
		}
	System.out.println(pi_6);
	long stop = System.currentTimeMillis();
	System.out.println("Elapsed Time = "+(stop-start)+" ms");

	}
}
