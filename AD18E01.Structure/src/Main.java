// ==================================================================================================================
// Exercise   : AD18E01.Structure
// Submission : https://judge.inf.ethz.ch/team/websubmit.php?cid=28777&problem=AD18E01
// Author     : 
// ==================================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class Main {

	public static class MaxHeap {
		//
		// We assume that the heap will not exceed MAX_HEAPSIZE length
		//
		final static int MAX_HEAPSIZE = 100000;		
		//
		// This field describes the number of elements that the heap holds
		//
		private int N = 0;
		//
		// The values of the heap are stored in this array
		//
		private int values[] = new int [MAX_HEAPSIZE];		
		//
		// Default empty constructor
		//
		public MaxHeap () { 
			// do nothing ...
		}
		//
		// For a given scanner, the heap will read the values. In this function, 
		// we assume that the values have partial order that satisfies the heap
		// condition.
		//
		public void readHeap (Scanner scanner) {			
			N = scanner.nextInt();
			for (int i = 0; i < N; i += 1) {
				values[i] = scanner.nextInt();
			}
		}
		private boolean cmp(int a, int b) {
			return a < b;
		}
		//
		// Helper function that is used in printing the state of the heap.
		//
		public void writeHeap (PrintStream out) {
			if (N > 0) {
				out.print(values[0]);
				for (int i = 1; i < N; i += 1) {
					out.print(" " + values[i]);
				}	
			}		
			out.println();
		}		

		// ====================================================================================================================
		// Complete the methods below. Feel free to add additional methods / fields if needed.
		// ====================================================================================================================

		//
		// We assume that values are already stored in the values[] array, but they
		// do not hold the heap condition and have arbitrary order. We need to 
		// restore the heap condition using the method below.
		//

		public int parentIndex(int index){
			return ((index + 1)/2) - 1;
		}

		public int leftIndex(int index) {
			return ((index + 1)*2) - 1;
		}

		public int rightIndex(int index) {
			return (index + 1)*2;
		}
		private int getParent(int index) {
			return values[parentIndex(index)];
		}

		private int getLeft(int index) {
			return values[leftIndex(index)];
		}

		private int getRight(int index) {
			return values[rightIndex(index)];
		}
		public void swap(int l, int r){
			int temp = values[l];
			values[l] = values[r];
			values[r] = temp;
		}
		public void siftUp(int k){
			int current = k;
			while(parentIndex(current) >= 0){
				if(getParent(current) < values[current]){
					swap(current, parentIndex(current));
				}
				current = parentIndex(current);
			}
		}
		public void buildHeap () {
			//
			// Your code goes here ...
			//
			//System.out.println(Arrays.toString(values));
			for (int i = N/2; i >= 0; i--) {
				restoreHeapCondition(i);
			}
		}
		private void restoreHeapCondition (int root) {
			int m = root, l, r;
			do {
				root = m;
				l = 2 * root + 1;
				r = 2 * root + 2;
				if (l < N && cmp(values[m], values[l])) m = l;
				if (r < N && cmp(values[m], values[r])) m = r;

				swap(root, m);
			} while (m != root);
		}
		public void siftDown(int index) {
			while(true){
				int maxIdx = index;
				if(leftIndex(index) < N  && getLeft(index) > values[maxIdx]) {
					maxIdx = leftIndex(index);
				}
				if(rightIndex(index) < N && getRight(index) > values[maxIdx]) {
					maxIdx = rightIndex(index);
				}
				if(maxIdx != index) {
					swap(index, maxIdx);
					index = maxIdx;
				}
				else{
					break;
				}
			}
		}
		
		//
		// Inserts a value in the heap, and places it on the right positions such
		// that the heap condition holds.
		//
		public void insert(int value) {			 			
			//
			// Your code goes here ...
			//
			values[N] = value;
			N++;
			siftUp(N-1);
		}
		
		//
		// Pops the first value from the heap, restoring the heap condition
		//
		public void deleteMax () {				
			//
			// Your code goes here ...
			//
			//writeHeap(System.out);
			swap(0,N-1);
			N--;
			siftDown(0);
		}		
		
		// ====================================================================================================================
		// End of implementation
		// ====================================================================================================================
	}

	//
	// Please, do not modify the read_and_solve method
	//
	public static void read_and_solve(InputStream in, PrintStream out) {
		//
		// Define a scanner that will read the input
		//
		Scanner scanner = new Scanner(in);
		//
		// Define a heap that will be used for the testing
		//
		MaxHeap heap = new MaxHeap();
		//
		// Read the number of test cases, and start executing
		//
		int T = scanner.nextInt();
		for (int test = 0; test < T; test += 1) {
			//
			// Read the command from the input
			//
			int command = scanner.nextInt();
			if (command == 1) {
				//
				// If command is '1' then, we must read an array from the input
				// then, build a heap out of the array, using the 'buildHeap'
				// method, and finally, output the heap on the screen.				
				//
				heap.readHeap(scanner);
				heap.buildHeap();
				heap.writeHeap(out);
			} else if (command == 2) {
				//
				// if the command is set to '2', then we read the heap values,
				// which already have partial order that satisfies the heap
				// property. Then we read we insert M elements, reading the 
				// M number first.
				//
				heap.readHeap(scanner);				
				int M = scanner.nextInt();
				for (int i = 0; i < M; i += 1) {					
					heap.insert(scanner.nextInt());
				}				
				heap.writeHeap(out);				
			} else if (command == 3) {
				//
				// if the command is set to '3', then we read the heap values,
				// which already have partial order that satisfies the heap
				// property. Then we delete the maximum elements M times, 
				// after reading the M number first.
				//
				heap.readHeap(scanner);				
				int M = scanner.nextInt();
				for (int i = 0; i < M; i += 1) {
					heap.deleteMax();
				}
				heap.writeHeap(out);
			}
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