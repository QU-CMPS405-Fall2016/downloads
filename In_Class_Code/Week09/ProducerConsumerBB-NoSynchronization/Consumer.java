import java.util.*;

public class Consumer implements Runnable {
	private Buffer buffer;

	public Consumer(Buffer b) {
		buffer = b;
	}

	public void run() {
		Date message;

		while (true) {
			int sleeptime = (int) (5000 * Math.random());
			System.out.println("Consumer napping "+sleeptime+" milliseconds");
        	try {Thread.sleep(sleeptime);}
        	catch (InterruptedException e){}

			System.out.println("Consumer is awake and wants to consume.");
			message = (Date) buffer.remove();
		}
	}
}
