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
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
            }

            message = (Date) buffer.remove();
        }
    }
}
