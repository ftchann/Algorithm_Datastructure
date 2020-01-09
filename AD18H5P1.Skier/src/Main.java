// =======================================================================================================
// Exercise		: AD18H5P1.Skier
// Template URL	: https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD18H5P1.Skier.zip
// Author		:
// =======================================================================================================

import java.util.Stack;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.InputStream;
import java.io.PrintStream;

class Main {

	//
	// Note that we provide you the access to two data-structures: LinkedList and Stack.
	// 
	// LinkedList can be used to store the graph as an adjacency list (i.e. Adjazenzliste)
	// as defined in lecture available at [1]. java.util.Iterator is used along the LinkedList
	// class, to iterate though the adjacency list. For example:
	//
	//  LinkedList<Integer> adj = new LinkedList<Integer>();
	//  adj.add(1);
	//  adj.add(2);
	//  adj.add(3);
	//  adj.add(4);
	//
	//  Iterator<Integer> it = adj.listIterator();
	//	while (it.hasNext()) {
	//		int value = it.next();
	//		System.out.println(value);
	//	}  
	// 
	// java.util.Stack implements Stack structure in Java, as defined in [1] (i.e. Stapel). We can
	// use this structure to push and pop elements, necessary for some graph traversal algorithms 
	// such as Depth-first search (DFS) or Breadth-first search (BFS). To illustrate how to push
	// elements on the stack and then to print the whole stack, we can use the following:
	//
	//  Stack<Integer> stack = new Stack<Integer>();
	//  stack.push(1);
	//  stack.push(2);
	// 	stack.push(3);
	//  stack.push(4);
	//  
	//  while (!stack.isEmpty()) {
	//  	int value = stack.pop();
	//		System.out.println(value);
	//  }
	// 
	//
	// [1] https://www.cadmo.ethz.ch/education/lectures/HS18/DA/vorlesung/graphenalgorithmen1.pdf
	//

	public static void read_and_solve(InputStream in, PrintStream out) {
		//
		// Define the scanner and read the number of test cases
		//
		Scanner scanner = new Scanner(in);
		int T = scanner.nextInt();
		//
		// Iterate through each of the test cases
		//
		for (int test = 0; test < T; test +=1) {
			//
			// Read V & E that define the graph.
			//
			int V = scanner.nextInt();
			int E = scanner.nextInt();			
			//
			// Create and initialize the adjacency list to represent the graph
			//
			@SuppressWarnings("unchecked")
			LinkedList<Integer>[] adj = new LinkedList[V];
			for (int i = 0; i < V; i += 1) {
				adj[i] = new LinkedList<Integer>();
			}
			//
			// Now start reading the edges in the system
			//
			for (int i = 0; i < E; i += 1) {
				int from = scanner.nextInt();
				int end  = scanner.nextInt();				
				adj[from].add(end);
			}	        
									
			//
			// Implement the rest of the solution here and
			// print the result which will be either "Yes"
			// or "No" per test case:
			boolean[] visited = new boolean[V];
			int v = 0;

			for (int i = 0; i < V; i++) {
				if(!visited[i]){
					dfsutil(i, adj ,visited);
					v = i;
				}
			}

			visited = new boolean[V];
			dfsutil(v, adj, visited);
			boolean printyes = true;
			for (int i = 0; i < V; i++) {
				if(!visited[i]){
					if(printyes) {
						out.println("No");
						//System.out.println(test);
						printyes = false;
					}
				}
			}
			if(printyes) {
				//System.out.println(test);
				out.println("Yes");
			}
			//
			// out.println("Yes") | out.println("No")
			// 
			// Also feel free to define auxiliary function
			// and methods outside the context of the 
			// read_and_solve method.
			//						
		}
		scanner.close();
	}
	public static void dfsutil(int vertex, LinkedList<Integer>[] adj, boolean[] visited){
		visited[vertex] = true;
		for(int i = 0; i<adj[vertex].size(); i++){
			if(!visited[adj[vertex].get(i)]) {
				dfsutil(adj[vertex].get(i), adj, visited);
			}
		}
	}

	//
	// Do not modify the main method, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
}
