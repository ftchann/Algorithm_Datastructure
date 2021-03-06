//=======================================================================================================
// Exercise     : AD8H13P3.Flea_Market
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD8H13P3.Flea_Market.zip
// Author       :
//=======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
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
			for (int s = 1; s <= S; s++) {
				for (int w = 0; w <= W; w++) {
					dp[0][s][w] = Integer.MIN_VALUE;
				}
			}
			for (int i = 1; i <= n ; i++) {
				for (int s = 0; s <= S; s++) {
					for (int w = 0; w <= W ; w++) {
						int wi = weight[i];
						int si = space[i];
						int pi = price[i];
						if(w-wi >= 0) {
							dp[i][s][w] = Math.max(dp[i-1][Math.max(0,s-si)][w-wi] + pi, dp[i-1][s][w]);
						}else{
							dp[i][s][w] =dp[i-1][s][w];
						}
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