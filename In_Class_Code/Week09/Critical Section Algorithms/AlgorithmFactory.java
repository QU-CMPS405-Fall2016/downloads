
public class AlgorithmFactory{
   public static void main(String args[]) {
	if  (args.length != 1) {
		System.err.println("Usage: java AlgorithmFactory [1|2|3]");
		System.exit(0);
	}

    MutualExclusion algorithm;
	int choice = Integer.parseInt(args[0].trim());

	switch(choice){
		case 1:
			algorithm = new Algorithm_1();
			System.out.println("Testing Algorithm 1");
			break;
		case 2:
			algorithm = new Algorithm_2();
			System.out.println("Testing Algorithm 2");
			break;
		case 3:
			algorithm = new Algorithm_3();
			System.out.println("Testing Algorithm 3");
			break;
		default:
			throw new IllegalArgumentException();
	}


	Thread first = new Thread(new Worker("Worker 0", 0, algorithm));
	Thread second = new Thread(new Worker("Worker 1", 1, algorithm));

    first.start();
    second.start();
  }
}
