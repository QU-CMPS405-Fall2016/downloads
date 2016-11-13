
public class Algorithm_1 implements MutualExclusion{
   private volatile int turn;

   public Algorithm_1() {
      turn = 0;
   }

   public void enteringCriticalSection(int t) {
	while (turn != t)
		Thread.yield();
   }

   public void leavingCriticalSection(int t) {
	turn = 1 - t;
   }
}
