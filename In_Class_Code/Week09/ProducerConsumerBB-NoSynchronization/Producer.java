import java.util.*;

public class Producer implements Runnable {
	private Buffer buffer;

	public Producer(Buffer b) {
		buffer = b;
	}

	public void run() {
		Date message;

		while (true) {
			int sleeptime = (int) (5000 * Math.random());
			System.out.println("Producer napping "+sleeptime+" milliseconds");
        	try {Thread.sleep(sleeptime);}
        	catch (InterruptedException e){}

			// produce an item & enter it into the buffer
			message = new Date();
			System.out.println("Producer is a wake and produced " + message);
			buffer.insert(message);
		}
	}
}
