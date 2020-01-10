//=======================================================================================================
// Exercise     : AD18H7P1.Bitcoins
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD18H7P1.Bitcoins.zip
// Author       :  
//=======================================================================================================

// Do they really expect me to write a min and maxheap?

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
	
	//
	// Define new methods / fields / classes to help you with the exercise
	//

	public static void read_and_solve(InputStream in, PrintStream out) {
		//
		// Define a scanner that will read the input
		//
		Scanner scanner = new Scanner(in);
		int Q = scanner.nextInt();
		PriorityQueue<Integer> Maxheap = new PriorityQueue<Integer>();
		PriorityQueue<Integer> Minheap = new PriorityQueue<Integer>();
		int maxValue = Integer.MIN_VALUE;


		while (Q-- > 0) {
			int command = scanner.nextInt();
			if (command == 1) {				
				//
				// Insertion
				//
				int value = scanner.nextInt();
				if(value > maxValue) maxValue = value;
				if((Maxheap.size() + Minheap.size()) % 3 == 2){
					Minheap.add(value);
				}else {
					Maxheap.add(-value);
				}
				if(Minheap.size() >0) {
					if (Minheap.peek() < -(Maxheap.peek())) {
						Maxheap.add(-Minheap.remove());
						Minheap.add(-Maxheap.remove());
					}
				}
			} else if (command == 2) {				
				//
				// Reporting
				//				
				// out.println ( ... )
				if(Minheap.size() == 0){
					out.println("Not enough transactions");
				}else{
					out.println(Minheap.peek() + " - " + maxValue);
				}
			}
		}
			
		scanner.close();
	}

	//
	// Do not modify the main method, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
}
