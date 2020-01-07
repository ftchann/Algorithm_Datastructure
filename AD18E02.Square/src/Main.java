// ==================================================================================================================
// Exercise   : AD18E02.Square
// Submission : https://judge.inf.ethz.ch/team/websubmit.php?cid=28777&problem=AD18E02
// Author     : 
// ==================================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Main {		
	//
	// Add additional methods, classes, fields, etc, to fit your needs.
	//
	// int someField = ...
	
	//
	// Feel free to modify the template, but do not rename the 
	// read_and_solve method, nor change its arguments.
	//
	public static void read_and_solve(InputStream in, PrintStream out) {
		//
		// Define a scanner that will read the input
		//
		Scanner scanner = new Scanner(in);
		//
		// Read the number of test cases
		//
		int T = scanner.nextInt();
		//
		// Now solve each case
		//		
		for (int test = 0; test < T; test += 1) {
			//
			// Read the number of rows of the matrix
			//
			int M = scanner.nextInt(); 
			//
			// Read the number of columns of the matrix
			//
			int N = scanner.nextInt(); 
			//
			// Allocate the matrix
			//
			int B[][] = new int[M][N];			
			//
			// Read the matrix
			//
			for (int i = 0; i < M; i += 1) {
				for (int j = 0; j < N; j += 1) {                					
					B[i][j] = scanner.nextInt();            						
				}
			}				
			//
			// Provide your solution here. Feel free to modify the template adding or 
			// removing variables to fit your needs. Find the largest square and store
			// it's area in the maxArea variable:
			//
			int maxlength = 0;
			int dp[][] = new int[M][N];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if(B[i][j] == 1){
						int a=0;
						int b=0;
						int c=0;
						if(i-1>=0){
							a=dp[i-1][j];
						}
						if(j-1>=0){
							b=dp[i][j-1];
						}
						if(j > 0 && i > 0)
							c=dp[i-1][j-1];
						dp[i][j] = min(a,b,c) +1;
						maxlength = max(maxlength, dp[i][j]);
					}
				}
			}
			int maxArea = maxlength * maxlength;
			//
			// Once done, output the result:
			//
			out.println(maxArea);
		}
		//
		// Finally, close the scanner once done
		//
		scanner.close();
	}
	public static int min(int a, int b){
		return a<b ? a: b;
	}
	public static int max(int a, int b){
		return a>b ? a: b;
	}
	public static int min(int a, int b, int c){
		return min(a,min(b,c));
	}
	//
	// Do not modify the main method, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
}