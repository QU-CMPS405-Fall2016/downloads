public class Algorithm_2 implements MutualExclusion{
   private volatile boolean flag0;
   private volatile boolean flag1;

   public Algorithm_2() {
	flag0 = false;
	flag1 = false;
   }

   public void enteringCriticalSection(int t) {
		if (t == 0) {
			flag0 = true;
			while(flag1 == true)
				Thread.yield();
		}
		else {
			flag1 = true;
			while (flag0 == true)
				Thread.yield();
		}
   }

   public void leavingCriticalSection(int t) {
		if (t == 0)
			flag0 = false;
		else
			flag1 = false;
   }
}
