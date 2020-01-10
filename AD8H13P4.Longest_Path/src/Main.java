//=======================================================================================================
// Exercise     : AD8H13P4.Longest_Path
// Template URL : https://www.cadmo.ethz.ch/education/lectures/HS18/DA/uebungen/AD8H13P4.Longest_Path.zip
// Author       :  
//=======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;


class Main {	

	public static ArrayList<Integer> topoSort(List<List<Integer>> v_out, int n){
		boolean visited[] = new boolean[n];
		ArrayList<Integer> order = new ArrayList<Integer>();
		for (int u = 0; u < n; u++) {
			if(!visited[u])
				visit(v_out, order,visited, u);
		}
		Collections.reverse(order);
		return order;
	}

	public static void visit(List<List<Integer>> v_out, ArrayList<Integer> order, boolean[] visited, int u){
		if(visited[u])
			return;
		visited[u] = true;

		for (int i = 0; i < v_out.get(u).size(); i++) {
			int v = v_out.get(u).get(i);
			if(!visited[v]){
				visit(v_out, order, visited, v);
			}
		}
		order.add(u);
	}

	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);
		//
		// Read the number of paragraphs and page width
		//
		int case_no, cases = scanner.nextInt();
		for (case_no = 1; case_no <= cases; case_no++) {
			//
			// Read the number of vertices and edges
			//
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			int i;
			//
			// There are several representations of the graph, choose what seems
			// useful:
			//
			// For every edge, the source and target vertex
			//
			int[] edge_from = new int[m];
			int[] edge_to   = new int[m];
			//
			// For every vertex V, List of vertices X with direct edge from V to X
			// 
			List<List<Integer>> v_out = new ArrayList<List<Integer>>();
			//
			// For every vertex V, List of vertices X with direct edge to V from X
			// 
			List<List<Integer>> v_in  = new ArrayList<List<Integer>>();

			// Create the empty lists
			for (i = 0; i < n; i++) {
				v_out.add (new ArrayList<Integer>());
				v_in.add  (new ArrayList<Integer>());
			}
			//	
			// Read elements of A and elements of B
			//
			for (i = 0; i < m; i++) {
				// Read the edge
				int s = scanner.nextInt();
				int t = scanner.nextInt();
				// Insert into all three graph representations
				edge_from[i] = s;
				edge_to[i] = t;
				v_out.get(s).add(t);
				v_in.get(t).add(s);
			}

			// First, compute the topological order
			int[] DP = new int[n];
			// The List to hold the topological order, empty reverse order
			ArrayList<Integer> order = topoSort(v_out, n);
			//System.out.println(order);
			int result = 0;
			for (int u: order) {
				int max = 0;
				for (int v: v_in.get(u)){
					max = Math.max(DP[v] , max);
				}
				DP[u] = max + 1;
				result = Math.max(DP[u], result);
			}
			// TODO: Put your implementation here

			// TODO: Output the length of the longest path
			out.println(result);
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