 public class Algorithm_3 implements MutualExclusion{
    private volatile boolean flag0;
    private volatile boolean flag1;
    private volatile int turn;
    
    public Algorithm_3() {
	flag0 = false;
	flag1 = false;
	turn = 0;
   }

   public void enteringCriticalSection(int t) {
		int other = 1 - t;
		turn = other;

		if (t == 0) {
			flag0 = true;
			while ( (flag1 == true) && (turn == other) )
				Thread.yield();
		}
		else {
			flag1 = true;
			while ( (flag0 == true) && (turn == other) )
				Thread.yield();
		}
   }

   public void leavingCriticalSection(int t) {
		if(t == 0)
			flag0 = false;
		else
			flag1 = false;
   }
}
