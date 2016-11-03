import java.util.Random;


public class MyMain {
	final static int NUMS = 100000000;
	final static int NUM_THREADS = 5;
	
	public static void main(String[] args) {		
		int[] arr = new int[NUMS];
	
		// Generate the random values
		Random r = new Random();	
		for(int i = 0; i < NUMS; i++) {
			arr[i] = r.nextInt(10);
		}
		
		// Create and start all the threads
		SimpleThread[] st = new SimpleThread[NUM_THREADS];
		for(int i = 0; i < NUM_THREADS; i++) {
			st[i] = new SimpleThread(arr, i*NUMS/NUM_THREADS, (i+1)*NUMS/NUM_THREADS);
			st[i].start();
		}

		// Join the threads and tally the final sum
		long sum = 0;
		for(int i = 0; i < NUM_THREADS; i++) {
			try {
				st[i].join();
			} catch (InterruptedException e) {
				System.out.println("There was a problem with the join!\n");
				e.printStackTrace();
			}
			
			sum += st[i].getSum();
		}
		
		System.out.printf("The sum is %d\n", sum);
	}

}
