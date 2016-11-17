import java.util.Arrays;

public class DataStore {
	private int[] arr;
	int head;
	int tail;
	
	public DataStore(int howMany) {
		this.arr = new int[howMany];
		this.head = 0;
		this.tail = 0;
	}

	// Get howMany numbers from the array and return them in a new array.
	public int[] getNums(int howMany) {
		if (head == arr.length) {
			return null;
		}
		
		int ret[] = Arrays.copyOfRange(arr, head, head+howMany);
		head = head + howMany;
		return ret;
	}

	// Add a new number to the array
	public void addNum(int newNum) {
		arr[tail++] = newNum;
	}
}
