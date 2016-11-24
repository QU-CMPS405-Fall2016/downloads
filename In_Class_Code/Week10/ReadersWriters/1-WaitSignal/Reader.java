
public class Reader implements Runnable {

    private RWLock db;
    private int readerNum;

    public Reader(int readerNum, RWLock db) {
        this.readerNum = readerNum;
        this.db = db;
    }

    public void run() {
        int sleeptime;
        final int NAP_TIME = 5;

        while (true) {
            // have a nap
            sleeptime = (int) (NAP_TIME * Math.random());
            try {
                Thread.sleep(sleeptime * 1000);
            } catch (InterruptedException e) {
            }

            //Wake up to read
            System.out.println("reader " + readerNum + " wants to read.");
            db.acquireReadLock(readerNum);

            // you have access to read from the database: let's read for awhile .....
            sleeptime = (int) (NAP_TIME * Math.random());
            try {
                Thread.sleep(sleeptime * 1000);
            } catch (InterruptedException e) {
            }

            db.releaseReadLock(readerNum);
        }
    }
}
