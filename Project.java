import java.util.*;

public class Project {
	
	static class Node {
		public String aString;
		public int steps;
		
		public Node(String thisString, int theSteps) {
			this.aString = thisString;
			this.steps = theSteps;
		}
	}
	
	public static String reverse(String aString, int index) {

		char temp [] = aString.toCharArray();
		int i = 0;
		while(i < index) {
			char aChar = temp[i];
			temp[i] = temp[index - 1];
			temp[index - 1] = aChar;
			i++;
			index--;
		}
		//System.out.println(String.valueOf(temp));
		return String.valueOf(temp);
		
	}	
	
	public static int sortByReversals(int[] arr) {
		
		String begin = "";
		String end = "";
		String init = "";
		String manipulated = "";
		
		Node temp = null;
		
		//copy the original array to begin
		for(int i = 0; i < arr.length; i++) {
			begin += arr[i];	
		}
		
		//sort the original array to compare to sorting by reversals implementation
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr [j - 1]) {
					int temporary = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temporary;
				}
			}
		}
		
		//copy sorted array into the destination array
		for(int i = 0; i < arr.length; i++) {
			end += arr[i];	
		}
		
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(begin, 0));
		
		if(begin == end) {
			return 0;
		}
		
		while(q.size() != 0) {
			//System.out.println();
			//front node in the temp value
			temp = q.poll();
			
			init = temp.aString;
			for(int i = 2; i <= arr.length; i++) {
				manipulated = init;
				manipulated = reverse (manipulated , i);
				
				if(manipulated.equals(end)) {
					
					//System.out.println(manipulated);
					return temp.steps + 1;
				}
				q.add(new Node(manipulated, temp.steps + 1));
			}
			
			
		}
		//didn't work
		return Integer.MIN_VALUE;
	}
	public static void main(String []args) {
		long startTime = System.nanoTime();
		
		int[] b = new int[25];
		int highest = Integer.MIN_VALUE;
		for(int i = 0; i < 25; i++) {
			int a[] = new int[10];
			for(int j = 0; j < a.length; j++) {
				a[j] = (int)(10.0 * Math.random()); 
				System.out.print(a[j] + " ");
			}
			System.out.println();
			int numReversals = sortByReversals(a);
			b[i] = numReversals;
			if(numReversals > highest) {
				highest = numReversals;
			}
			System.out.println(numReversals);
			System.out.println();
		}
		long stopTime = System.nanoTime();
		System.out.println("Average time to sort: " + (stopTime - startTime) / 25);
		System.out.println("most reversals: " + highest);
		int total = 0;
		for(int i = 0; i < 25; i++) {
			total += b[i];		
		}
		System.out.println("Average reversals: " + ((double)total / 25));
	}
}
