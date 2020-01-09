// =======================================================================================================
// Exercise		: AD18H3P1.Biker
// Template URL	: https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD18H3P1.Biker.zip
// Author		:
// =======================================================================================================

import java.io.*;
import java.util.Scanner;

class Main {
	//
	// The solution
	//
	public static void read_and_solve(InputStream in, PrintStream out) {
		//
		// Define the min, max and elevation gain;
		//
		int max = 0, min = 0, elevation = 0;				
		//
		// Define the scanner and read the number of tracking points
		//
		Scanner scanner = new Scanner(in);
		int n = scanner.nextInt();
		
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		// provide the solution here ....
		// use scanner.nextInt() to read the next number in the sequence ...
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		int last=0;
		for (int i = 0; i < n; i++) {
			int current = scanner.nextInt();
			max = Math.max(max, current);
			min = Math.min(min, current);
			if(i!= 0){
				if(current - last >= 0){
					elevation += current-last;
				}
			}
			last = current;
		}
		
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<		
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		//
		// Finally output the results
		//
		out.print("Maximum Height: "); out.println(max);
		out.print("Minimum Height: "); out.println(min);
		out.print("Elevation Gain: "); out.println(elevation);
		
		scanner.close();
	}
	
	//
	// Do not modify the main method, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
}
