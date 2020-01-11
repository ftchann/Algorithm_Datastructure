//=======================================================================================================
// Exercise     : AD8H13P3.Flea_Market
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD8H13P3.Flea_Market.zip
// Author       :  
//=======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class Main {

	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);

		int tests = scanner.nextInt();
		for (int t = 0; t < tests; t += 1) {
			//
			// Scan n, S, W
			//
			int n = scanner.nextInt();
			int S = scanner.nextInt();
			int W = scanner.nextInt();
			//
			// Allocate space
			//
			int [] space  = new int[n+1];
			int [] weight = new int[n+1];
			int [] price  = new int[n+1];
			//
			// Scan the values
			//
			for (int i = 1; i <= n; i += 1) {
				space [i] = scanner.nextInt();
				weight[i] = scanner.nextInt();
				price [i] = scanner.nextInt();
			}
			int[][][] dp = new int[n+1][S+1][W+1];
			for (int i = 1; i <= n ; i++) {
				for (int s = S; s >= 0; s--) {
					for (int w = 0; w <= W ; w++) {
						int wi = weight[i];
						int si = space[i];
						int pi = price[i];
						int a = 0;
						int b = 0;
						int c = 0;
						int d = 0;
						if(i-1 >= 1) {
							a=dp[i-1][s][w];
						}
						if(s+1 <= S) {
							b=dp[i][s+1][w];
						}
						if(w-1>=0) {
							c = dp[i][s][w-1];
						}
						if(w-wi >= 0) {
							d = dp[i-1][Math.max(0,s-si)][w-wi] + pi;
						}
						dp[i][s][w] = Math.max(Math.max(a,b), Math.max(c,d));
					}
				}
			}
			//System.out.println(Arrays.deepToString(dp));
			out.println(dp[n][S][W]);
			//
			// Provide your solution here
			//			
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