/**
 * BoundedBuffer.java
 *
 * <p>This program implements the bounded buffer with semaphores.
 */
public class BoundedBuffer implements Buffer {
    private static final int BUFFER_SIZE = 5;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;

    private int in; // points to the next free position in the buffer

    private int out; // points to the next full position in the buffer

    private Object[] buffer;

    public BoundedBuffer() {
        in = 0;
        out = 0;

        buffer = new Object[BUFFER_SIZE];

        mutex = new Semaphore(1);
        empty = new Semaphore(BUFFER_SIZE);
        full = new Semaphore(0);
    }

    public synchronized void insert(Object item) {
        System.out.println("Producer entered insert method");
        empty.acquire();
        mutex.acquire();

        System.out.println("Producer found an empty buffer, filling it");
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;

        mutex.release();
        full.release();
        System.out.println("Producer is leaving insert method");
    }

    public synchronized Object remove() {
        System.out.println("Consumer entered remove method");
        full.acquire();
        mutex.acquire();

        Object item;

        System.out.println("Consumer found something in the buffer, getting it out.");
        item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;

        mutex.release();
        empty.release();
        System.out.println("Consumer is leaving remove method");
        return item;
    }
}
