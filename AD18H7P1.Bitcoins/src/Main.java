//=======================================================================================================
// Exercise     : AD18H7P1.Bitcoins
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD18H7P1.Bitcoins.zip
// Author       :  
//=======================================================================================================

// Do they really expect me to write a min and maxheap?

import java.io.InputStream;
import java.io.PrintStream;
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
			
		while (Q-- > 0) {
			int command = scanner.nextInt();
			if (command == 1) {				
				//
				// Insertion
				//
				int V = scanner.nextInt();				
											
			} else if (command == 2) {				
				//
				// Reporting
				//				
				// out.println ( ... )
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