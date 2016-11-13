public class MutualExclusionUtilities{
   private static final int NAP_TIME = 5;

   public static void criticalSection(String name) {
      System.out.println(name + " in critical section");
      int sleeptime = (int) (NAP_TIME * Math.random() );
      try { Thread.sleep(sleeptime*1000); }
      catch (InterruptedException e) {}
   }
   
   public static void nonCriticalSection(String name) {
      System.out.println(name + " out of critical section");
      int sleeptime = (int) (NAP_TIME * Math.random() );
      try { Thread.sleep(sleeptime*1000); }
      catch (InterruptedException e) {}
   }
}
