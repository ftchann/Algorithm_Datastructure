// =======================================================================================================
// Exercise		: AD18H3P2.RomanNumbers
// Template URL	: https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD18H3P2.RomanNumbers.zip
// Author		:
// =======================================================================================================

import java.io.*;
import java.util.Scanner;

class Main {
	
	static int printRomanLetters(PrintStream out, int num, int coef, String letters) {
		while (num >= coef) {
			out.print(letters);
			num -= coef;
		}
		return num;
	}
	
	public static void printRomanNumber(PrintStream out, int num) 
	{			
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		// provide the solution here ....

		while(num > 0){
			if(num >= 1000){
				out.print("M");
				num -= 1000;
				continue;
			}
			if(num >= 900){
				out.print("CM");
				num -= 900;
				continue;
			}
			if(num >= 500){
				out.print("D");
				num -= 500;
				continue;
			}
			if(num >= 400){
				out.print("CD");
				num -= 400;
				continue;
			}
			if(num >= 100){
				out.print("C");
				num -= 100;
				continue;
			}
			if(num >= 90){
				out.print("XC");
				num -= 90;
				continue;
			}
			if(num >= 50){
				out.print("L");
				num -= 50;
				continue;
			}
			if(num >= 40){
				out.print("XL");
				num -= 40;
				continue;
			}
			if(num >= 10){
				out.print("X");
				num -= 10;
				continue;
			}
			if(num >= 9){
				out.print("IX");
				num -= 9;
				continue;
			}
			if(num >= 5){
				out.print("V");
				num -= 5;
				continue;
			}
			if(num >= 4){
				out.print("IV");
				num -= 4;
				continue;
			}
			if(num >= 1){
				out.print("I");
				num -= 1;
				continue;
			}
		}

		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<		
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		//
		// Then print a new line
		//
		out.println();
	}
	
	//
	// The solution
	//
	public static void read_and_solve(InputStream in, PrintStream out) 
	{
		//
		// Setup the scanner, and scan the number that are about 
		// to be converted into Roman numbers.
		//
		Scanner scanner = new Scanner(in);
		int n = scanner.nextInt();
		//
		// Now iterate through all numbers
		//
		for(int i = 0; i < n; i += 1) {			
			int num = scanner.nextInt();
			printRomanNumber(out, num);												
		}
		// 
		// Close the scanner once done.
		//
		scanner.close();
	}
	
	//
	// Do not modify the main file, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
}
