//=======================================================================================================
// Exercise     : AD8H11P2.LineBreaks
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD8H11P2.LineBreaks.zip
// Author       :  
//=======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

//No idea if it is O(N*W) or O(N*W*W) but should be O(N * W)

class Main {

	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);

		//
		// Read the number of paragraphs and page width
		//
		int paragraphs = scanner.nextInt();

		for (int paragraph_no = 1; paragraph_no <= paragraphs; paragraph_no++) {
			//
			// Read n, D and then the array d[i] and e[i]
			//
			int n = scanner.nextInt();
			int maxWidth = scanner.nextInt();
			scanner.nextLine(); // Eat the newline after the last number

			String[] words = new String[n];
			int[] wordlength = new int[n];
			int[] dp = new int[n];
			for (int i = 0; i < n; i++) {
				words[i] = scanner.nextLine();
				wordlength[i] = words[i].length();
			}
			dp[0] = (maxWidth-wordlength[0]) * (maxWidth-wordlength[0]);
			for (int i = 1; i < n; i++) {
				int min = Integer.MAX_VALUE;
				int newwordlength = wordlength[i];
				for(int j = i-1; j >= -1 && newwordlength <= maxWidth; j--){
					if(j == -1){
						min = Math.min(min, 0 + (maxWidth-newwordlength) * (maxWidth-newwordlength));
						break;
					}
					min = Math.min(min, dp[j] + (maxWidth-newwordlength) * (maxWidth-newwordlength));
					newwordlength += wordlength [j] + 1;
				}
				dp[i] = min;
			}
			// fix last row;
			int lengthlastline = wordlength[n-1];
			int min = Integer.MAX_VALUE;
			for (int i = n-2; i >= -1 ; i--) {
				if(lengthlastline <= maxWidth){
					if(i == -1){
						min = Math.min(min, 0);
						break;
					}
					min = Math.min(min, dp[i]);
					lengthlastline += wordlength[i] + 1;
				}else{
					break;
				}
			}
			dp[n-1] = min;
			out.println(dp[n-1]);


			//
			// Provide your solution here ...
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
