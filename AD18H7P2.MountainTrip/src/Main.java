// =======================================================================================================
// Exercise     : AD18H7P2.MountainTrip
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD18H7P2.MountainTrip.zip
// Author       :
// =======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Main
{		
	//
	// Note: 
	// 		- mountain_cities [i] is the position of the (i+1)-th mountain city 
	// 		- sea_cities      [i] is the position of the (i+1)-th sea city 
	// 		- trip_beginning  [i] and trip_end[i] are the beginning and ending 
	//		  					  kilometers of the (i+1)-th trip
	// 
	// The number of elements of an array A can be accessed using A.length
	//
	public static int solve (int n, int[] mountain_cities, int[] sea_cities, int[] trip_beginning, int[] trip_end)
	{		
		//
		// Provide your solution here
		//		
		return -1;
	}
	
	public static void read_and_solve(InputStream in, PrintStream out) {
		//
		// Define the scanner and read the number of test cases
		//
		Scanner scanner = new Scanner(in);
		int testCount = scanner.nextInt();

		for(int testIdx = 0; testIdx < testCount; testIdx += 1)
		{			
			int n = scanner.nextInt();
			int M = scanner.nextInt();
			int S = scanner.nextInt();
			int T = scanner.nextInt();			
			//
			// Create space for the cities / trips
			//
			int[] mountain_cities = new int[M];
			int[] sea_cities 	  = new int[S];
			int[] trip_beginning  = new int[T];
			int[] trip_end 		  = new int[T];
			//
			// Read the input
			//
			for(int i = 0; i < M; i += 1) mountain_cities[i] = scanner.nextInt();			
			for(int i = 0; i < S; i += 1) sea_cities[i] 	 = scanner.nextInt();
			for(int i = 0; i < T; i += 1) trip_beginning[i]  = scanner.nextInt();			
			for(int i = 0; i < T; i += 1) trip_end[i] 		 = scanner.nextInt();
			//
			// Now solve it
			//
			out.println(solve(n, mountain_cities, sea_cities, trip_beginning, trip_end));
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
