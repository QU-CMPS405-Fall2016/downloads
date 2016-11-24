
public class Database implements RWLock {

    private int readerCount; // the number of active readers
    private boolean dbWriting; // flag indicating write use

    public Database() {
        readerCount = 0;
        dbWriting = false;
    }

    public synchronized void acquireReadLock(int readerNum) {
        while (dbWriting == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        ++readerCount;

        System.out.println("Reader " + readerNum + " is reading. Reader count = " + readerCount);
    }

    public synchronized void releaseReadLock(int readerNum) {
        --readerCount;

        // if I am the last reader tell all others that the database is no longer being read
        if (readerCount == 0) notify();

        System.out.println(
                "Reader " + readerNum + " is done reading. Reader count = " + readerCount);
    }

    // writer will call this when they start writing
    public synchronized void acquireWriteLock(int writerNum) {
        while (readerCount > 0 || dbWriting == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        // once there are either no readers or writers,indicate that the database is being written
        dbWriting = true;

        System.out.println("writer " + writerNum + " is writing.");
    }

    public synchronized void releaseWriteLock(int writerNum) {
        dbWriting = false;

        System.out.println("writer " + writerNum + " is done writing.");
        notifyAll(); // maybe more than one reader waiting to read, notifyAll
    }
}
