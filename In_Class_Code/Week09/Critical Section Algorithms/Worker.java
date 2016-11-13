
public class Worker implements Runnable{
	private String name;
	private int id;
   	private MutualExclusion mutex;

   public Worker(String name, int id, MutualExclusion mutex) {
      this.name = name;
      this.id = id;
      this.mutex = mutex;
   }

   public void run() {
      while (true) {
         mutex.enteringCriticalSection(id);
         MutualExclusionUtilities.criticalSection(name);
         mutex.leavingCriticalSection(id);
         MutualExclusionUtilities.nonCriticalSection(name);
      }
   }
}
