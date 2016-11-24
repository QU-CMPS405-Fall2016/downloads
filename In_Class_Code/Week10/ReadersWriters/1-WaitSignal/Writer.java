
public class Writer implements Runnable {
    private RWLock server;
    private int writerNum;

    public Writer(int w, RWLock db) {
        writerNum = w;
        server = db;
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

            //wake up to write
            System.out.println("writer " + writerNum + " wants to write.");
            server.acquireWriteLock(writerNum);

            // you have access to write to the database: write for awhile ...
            sleeptime = (int) (NAP_TIME * Math.random());
            try {
                Thread.sleep(sleeptime * 1000);
            } catch (InterruptedException e) {
            }

            server.releaseWriteLock(writerNum);
        }
    }
}
