public class SimpleThread extends Thread {
	DataStore ds;
	long sum = 0;
	long howm = 0;

	public SimpleThread(DataStore ds) {
		this.ds = ds;
	}

	public long getSum() {
		return sum;
	}

	public void run() {
		int arr[] = ds.getNums(10);

		while (arr != null) {
			howm += 10;

			for (int i : arr) {
				sum += i;
			}
			arr = ds.getNums(10);
		}

		System.out.printf("Threading quiting. Sum is %d and How Many is %d\n", 
				sum, howm);
	}
}
