//=======================================================================================================
// Exercise     : AD8H13P2.Jungle
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD8H13P2.Jungle.zip
// Author       :  
//=======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
class Position implements Comparable<Position>{
	int weight;
	int x;
	int y;
	public Position(int weight, int x, int y){
		this.weight = weight;
		this.x = x;
		this.y = y;
	}
	public int compareTo(Position other){
		if(this.weight > other.weight)
			return 1;
		else if(this.weight < other.weight)
			return -1;
		return 0;
	}
}

class Main {

	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);
		//
		// Read the number of cases and loop over the cases
		//
		int case_no, cases = scanner.nextInt();
		
		for (case_no = 0; case_no < cases; case_no += 1) {
			//
			// Read the jungle size
			//
			int n = scanner.nextInt();
			//
			// Create a new 2D array for the times
			//
			int[][] T = new int[n][n]; 
			//
			// Read the times
			//
			int startx=0, starty = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					T[i][j] = scanner.nextInt();
					if(T[i][j] == 0){
						startx = i;
						starty = j;
					}
				}
			}
			PriorityQueue<Position> Q = new PriorityQueue<Position>();
			boolean visited[][] = new boolean[n][n];
			Q.add(new Position(0,startx,starty));
			while (!Q.isEmpty()){

				Position pos = Q.poll();
				int currx = pos.x;
				int currweight = pos.weight;
				int curry = pos.y;
				if(visited[currx][curry]){
					continue;
				}else {
					visited[currx][curry] = true;
					if (pos.x == 0 || pos.x == n - 1 || pos.y == 0 || pos.y == n - 1) {
						out.println(currweight);
						break;
					} else {
						if (!visited[currx - 1][curry]) {
							Q.add(new Position(currweight + T[currx - 1][curry], currx - 1, curry));
						}
						if (!visited[currx + 1][curry]) {
							Q.add(new Position(currweight + T[currx + 1][curry], currx + 1, curry));
						}
						if (!visited[currx][curry - 1]) {
							Q.add(new Position(currweight + T[currx][curry - 1], currx, curry - 1));
						}
						if (!visited[currx][curry + 1]) {
							Q.add(new Position(currweight + T[currx][curry + 1], currx, curry + 1));
						}
					}
				}
			}
			//
			// Provide your solution here


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