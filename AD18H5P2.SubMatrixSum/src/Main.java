// =======================================================================================================
// Exercise		: AD18H5P2.SubMatrixSum
// Template URL	: https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD18H5P2.SubMatrixSum.zip
// Author		:
// =======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class Main
{
	//
	// Here we can define as many fields as we need to
	//
	
	static void preprocess()
	{
		//
		// Here we can implement the pre-processing routine ...
		//
	}

	static int query(int a, int b, int c, int d)
	{
		//
		// Here we can implement the querying routine
		//
		return 0;
	}
	
	public static void read_and_solve(InputStream in, PrintStream out)
	{
		Scanner scanner = new Scanner(in);
		int n = Integer.parseInt(scanner.nextLine());
		String line = scanner.nextLine();
		String[] arrOfStr = line.split(" ");
		int m=arrOfStr.length;
		int[][] A = new int[n][m];
		for (int i = 0; i < m; i++) {
			A[0][i] = Integer.parseInt(arrOfStr[i]);
		}
		for (int i = 1; i < n; i++) {
			line = scanner.nextLine();
			arrOfStr = line.split(" ");
			for (int j = 0; j < m; j++) {
				A[i][j] = Integer.parseInt(arrOfStr[j]);
			}
		}
		int[][] B = new int[n+1][m+1];

		for (int x = 1; x < m+1; x++) {
			for (int y = 1; y < n+1; y++) {
				B[y][x] = B[y-1][x] + B[y][x-1] - B[y-1][x-1] + A[y-1][x-1];
			}
		}
		//System.out.println(Arrays.deepToString(B));
		int p = Integer.parseInt(scanner.nextLine());
		int ix, iy, jx, jy;
		for (int i = 0; i < p; i++) {
			line = scanner.nextLine();
			arrOfStr = line.split(" ");
			ix = Integer.parseInt(arrOfStr[0]);
			jx = Integer.parseInt(arrOfStr[1]);
			iy = Integer.parseInt(arrOfStr[2]);
			jy = Integer.parseInt(arrOfStr[3]);
			out.println(B[jx][jy] - B[jx][iy-1] - B[ix-1][jy] + B[ix-1][iy-1]);
		}
		//
		// Implement the rest of the routine that reads the input.
		//
		
		scanner.close();
	}


	//
	// Do not modify the main method, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
}
