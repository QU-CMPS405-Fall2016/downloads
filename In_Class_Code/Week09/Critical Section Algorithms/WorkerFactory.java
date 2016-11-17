
public class WorkerFactory {

   	public WorkerFactory() {
    	/**create a MutualExclusion lock based on either
    	 *Algorithm_1(), Algorithm_2(), or Algorithm_3()
    	 *implementation of a lock.
    	 */
    	MutualExclusion lock = new Algorithm_3();
      	(new Thread(new Worker("W1",0,lock))).start();
      	(new Thread(new Worker("W2",1,lock))).start();
   	}

   	public static void main(String[] args){
		new WorkerFactory();
   	}
}
