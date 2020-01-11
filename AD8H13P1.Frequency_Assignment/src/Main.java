//================================================================================================================
// Exercise     : AD8H13P1.Frequency_Assignment
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD8H13P1.Frequency_Assignment.zip
// Author       :  
//================================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class Main {
		
	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);
		int ntestcases = scanner.nextInt();
		
		for(int testno = 0; testno < ntestcases; testno += 1) {						
			int N = scanner.nextInt();			
			int m = scanner.nextInt();
			
			int [][] v = new int[m+1][N+1];
			int [][] h = new int[m+1][N+1];

			for(int j = 1; j <= m; j += 1) {
				for (int i = 1; i <= N; i += 1) {
					v[j][i] = scanner.nextInt();
				}
				for (int i = 1; i <= N - 2; i += 1) {
					h[j][i] = scanner.nextInt();
				}
			}
			//System.out.println(Arrays.deepToString(v));
			//System.out.println(Arrays.deepToString(h));

			int dp[][] = new int[m+1][N+1];
			int[] Best = new int[N+1];
			int[] Sbest = new int[N+1];

			for (int i = 1; i < N+1; i++) {
				for (int j = 0; j < m+1; j++) {
					if(i == 1){
						dp[j][i]=v[j][i] + dp[prev(Best,Sbest ,j,i-1)][i-1];
					}else if(i==2){
						int a = v[j][i] + dp[prev(Best,Sbest ,j,i-1)][i-1];
						int b = v[j][i] + v[j][i-1]+ dp[prev(Best,Sbest ,j,i-2)][i-2];
						dp[j][i] = Math.max(a,b);
					}
					else{
						int a = v[j][i] + dp[prev(Best,Sbest ,j,i-1)][i-1];
						int b = v[j][i] + v[j][i-1]+ dp[prev(Best,Sbest ,j,i-2)][i-2];
						int c = v[j][i] + v[j][i-1]+ v[j][i-2]+h[j][i-2]+ dp[prev(Best,Sbest ,j,i-3)][i-3];
						dp[j][i] = Math.max(a,Math.max(c,b));
					}
					if(dp[j][i] >= dp[Best[i]][i]){
						Sbest[i] = Best[i];
						Best[i] = j;
					}else if(dp[j][i] > dp[Sbest[i]][i]){
						Sbest[i] = j;
					}
				}
			}
			//
			// Provide your solution here ....
			//
			out.println( dp[Best[N]][N]);
		}
		scanner.close();
	}
	public static int prev(int[] Best, int[] Sbest, int j, int i){
		if(Best[i] != j)
			return Best[i];
		else{
			return Sbest[i];
		}
	}

	//
	// Do not modify the main method, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
}