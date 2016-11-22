
public class Factory {
    public static void main(String args[]) {
        Buffer server = new BoundedBuffer();
        Thread producerThread = new Thread(new Producer(server));
        Thread consumerThread = new Thread(new Consumer(server));

        producerThread.start();
        consumerThread.start();
    }
}
