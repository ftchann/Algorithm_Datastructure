//=======================================================================================================
// Exercise     : AD18H9P1.RealCoins
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD18H9P1.RealCoins.zip
// Author       :  
//=======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.math.BigInteger;

class Main {
		
	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);
		//
		// Get N & K
		//
		int N = scanner.nextInt();
		int K = scanner.nextInt();

		int v[] = new int[N];
		BigInteger[] d = new BigInteger[K];


		long sum = 0;
		for (int i = 0; i < N; i++) {
			v[i] = scanner.nextInt();
			sum+=v[i];
		}
		if (sum < K * 2){
			out.println("0");
		}else{
			d[0] = BigInteger.ONE;
			for (int i = 1; i < K; i++) {
				d[i] = BigInteger.ZERO;
			}
			for (int i = 0; i < N; i++) {
				for (int j = K-1; j >= v[i] ; j--) {
					d[j] = (d[j-v[i]]).add(d[j]);
				}
			}

			BigInteger invalid = BigInteger.ZERO;
			for (int i = 0; i < K; i++) {
				invalid = invalid.add(d[i]);
			}
			BigInteger two = BigInteger.TWO;
			invalid = invalid.multiply(two);

			BigInteger power = two.pow(N);
			BigInteger result = power.subtract(invalid);
			//
			// Provide your solution here ...
			//
			out.println(result.toString());
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