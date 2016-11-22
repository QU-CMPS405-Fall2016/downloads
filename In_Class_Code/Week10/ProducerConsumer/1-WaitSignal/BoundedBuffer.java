// * This program implements the bounded buffer using Java synchronization.

public class BoundedBuffer implements Buffer {
    private static final int BUFFER_SIZE = 5;

    private int count; // number of items in the buffer

    private int in; // points to the next free position in the buffer

    private int out; // points to the next full position in the buffer

    private Object[] buffer;

    public BoundedBuffer() {
        count = 0;
        in = 0;
        out = 0;

        buffer = new Object[BUFFER_SIZE];
    }

    public synchronized void insert(Object item) {
        System.out.println("Producer entered insert method");
        while (count == BUFFER_SIZE) {
            try {
                System.out.println("Producer could not find an empty buffer, calling wait()");
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Producer found an empty buffer, filling it");
        ++count;
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;

        notify();
        System.out.println("Producer called notify() and is now leaving insert");
    }

    public synchronized Object remove() {
        Object item;
        System.out.println("Consumer entered remove method");
        while (count == 0) {
            try {
                System.out.println(
                        "Consumer could not find anything in the buffer, calling wait()");
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Consumer found something in the buffer, getting it out.");
        --count;
        item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;

        notify();
        System.out.println("Consumer called notify() and is now leaving remove");
        return item;
    }
}
