public class Factory {
    public Factory() {
        Buffer buffer = new BoundedBuffer();
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }

    public static void main(String args[]) {
        new Factory();
    }
}
