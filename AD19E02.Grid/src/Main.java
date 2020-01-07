// ==================================================================================================================
// Exercise   : AD19E02.Grid
// Submission : https://judge.inf.ethz.ch/team/websubmit.php?cid=28781&problem=AD19E02
// Author     : 
// ==================================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Main {	
	//
	// Provide the solution of the grid problem in this function. 
	// Feel free to provide additional fields and methods if 
	// necessary.
	//
	public static int solveGrid (int [][] grid) {		
		int ans = Integer.MAX_VALUE;
		int n= grid.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[0][i] = grid[0][i];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int a=Integer.MAX_VALUE;
				int b = dp[i-1][j];
				int c = Integer.MAX_VALUE;
				if(j-1 >= 0)
					a=dp[i-1][j-1];
				if(j+1 < n)
					c = dp[i-1][j+1];
				dp[i][j] = min(a,b,c) + grid[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			ans = min(ans, dp[n-1][i]);
		}
		//
		// Your code goes here ...
		//
		return ans;
	}
	public static int min(int a, int b){
		return a<b ? a:b;
	}
	public static int min(int a, int b, int c){
		return min(min(a,b),c);
	}

	//
	// Please, do not modify the read_and_solve method, as well as the main method
	//
	public static void read_and_solve(InputStream in, PrintStream out) {
		//
		// Define a scanner that will read the input
		//
		Scanner scanner = new Scanner(in);
		//
		// Read the number of test cases, and start executing
		//
		int T = scanner.nextInt();
		for (int test = 0; test < T; test += 1) {
			//
			// Read the size of the array and create a new one
			//
			int N = scanner.nextInt();
			int[][] grid = new int[N][];			
			for (int i = 0; i < N; i += 1) {
				grid[i] = new int[N];
				for (int j = 0; j < N; j += 1) {
					grid[i][j] = scanner.nextInt();
				}
			}
			out.println(solveGrid(grid));
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