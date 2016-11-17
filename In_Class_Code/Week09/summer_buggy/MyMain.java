import java.util.Random;


public class MyMain {
	final static int NUMS = 100000000;
	final static int NUM_THREADS = 5;
	
	public static void main(String[] args) {
		// Create the object that stores all the random numbers
		DataStore ds = new DataStore(NUMS);
	
		// Generate the random values.
		// Using a fixed seed of 123 ensures the program should be 
		// the same everytime.
		Random r = new Random(123);	
		for(int i = 0; i < NUMS; i++) {
			ds.addNum(r.nextInt(10));
		}
		
		// Create and start all the threads
		SimpleThread[] st = new SimpleThread[NUM_THREADS];
		for(int i = 0; i < NUM_THREADS; i++) {
			st[i] = new SimpleThread(ds);
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
