public class SimpleThread extends Thread {
	int[] arr;
	int start;
	int end;
	long sum = 0;
	
    public SimpleThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    
    public long getSum() {
    	return sum;
    }
    
    public void run() {   	
        for (int i = start; i < end; i++) {
        	sum += arr[i];
        }     
        
        System.out.printf("Threading quiting and sum is %d\n", sum);
    }  
}
