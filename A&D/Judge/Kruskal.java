// ==================================================================================================================
// Exercise   : AD19E01.Structure
// Submission : https://judge.inf.ethz.ch/team/websubmit.php?cid=28781&problem=AD19E01
// Author     : 
// ==================================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Main {
    //
	// Representation of an edge in the graph: 
	// u and v represent the vertices of the edge
	// and w represents its weight
	//
	public static class Edge implements Comparable<Edge> {		
		public int u; 	// vertex u of the edge 
		public int v; 	// vertex v of the edge
		public int w; 	// the weight of the edge		
		//
		// Edge initialization constructor 
		//
		public Edge (int u, int v, int w) {
			this.u = u;
			this.v = v;			
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			if(o.w - this.w > 0) {
				return -1;
			}else if(o.w -this.w < 0) {
				return 1;
			}else {
				return 0;
			}
		}
		
		public String toString() {
			return ""+this.w;
		}
	}
	//
	// Graph is represented with number of vertices (V)
	// number of edges (E) and the set of all edges,
	// represented as an array of Edge instances.
	//
	static class Graph {
		public int V; 			// number of vertices in the graph
		public int E;			// number of edges  in the graph
		public Edge[] edges;	// each edge in the graph	
		//
		// Graph initialization constructor 
		//
		public Graph(int V, int E) {
			this.V 		= V;
			this.E 		= E;
			this.edges 	= new Edge[E];
		}
	}

	//
	// Union-find is a data-structure that tracks a set of elements
	// partitioned into a number of disjoint (non-overlapping) subsets.
	//
	// ========================================================================
	// ============================ WARNING ===================================
	// ------------------------------------------------------------------------
	// == THE IMPLEMENTATION OF THE UNION-FIND DATASTRUCTURE IS INNEFICIENT ===
	// ======================================================================== 
	//
	// Please modify the function such that both `union` and `find` operate in
	// O(log(n)) time. Feel free to add fields, and modify the existing methods.
	// Initialize the newly created fields in the constructor or the `create`
	// method if necessary.
	//
	public static class UnionFind {
		int[] labels; // points to the parent of i, labels[i] = i then i is root
		int[] sz;
		//
		// The constructor, just calls `create`
		//
		public UnionFind (int N) {
			this.sz = new int[N];
			create(N);
		}		
		//
		// Initialize the union-data structure, by creating a 
		// set for each element from [0, 1, ..., N - 1]. 
		//
		void create (int N) {
			labels = new int[N];
			for (int i = 0; i < N; i += 1) { 
				labels[i] = i;
				sz[i] = 1; // Each component has originally size 1 
			}
		}		
		//
		// Determine which set a particular element belongs to. 
		// Return the label or the 'id' of the set for a given x
		//
		int find (int x) {
			int root = x;
			while(root != labels[root]) {
				root = labels[root];
			}
			
			while(x != root) {
				int next = labels[x];
				labels[x] = root;
				x = next;
			}
			return root; 
		}
		//
		// Connect or join two sets. In other words, change 
		// the family by replacing two sets, the one containing 
		// x and the one containing y, by a single set that is 
		// the union of these two sets. 
		//
		void union (int x, int y) {						
			int rootx = labels[x];
			int rooty = labels[y];
			if(rootx == rooty) {
				return;
			}
			if(sz[rootx] > sz[rooty]) {
				sz[rootx] += sz[rooty];
				labels[rooty] = rootx;
			}else {
				sz[rooty] += sz[rootx];
				labels[rootx] = rooty;
			}
		}
	}
	
	
	//
	// Calculate the minimum spanning tree (MST) using the Kruskal's algorithm
	// and return the cost of the graph
	//
	public static long kruskal (Graph G) {
		//
		// The cost of the MST will fit inside a long type.
		//
		long cost = 0;
		
		//
		// Complete the implementation of Kruskal's algorithm. Once you
		// compute the MST, compute the cost of the graph i.e. compute
		// the sum of the weights of all edges in the MST.
		//
		// Use the provided UnionFind data-structure available above.
		//
		UnionFind set = new UnionFind(G.V);
		
		Arrays.sort(G.edges);
		for(int i=0; i<G.edges.length; i++) {
			if(set.find(G.edges[i].u) != set.find(G.edges[i].v)) {
				set.union(G.edges[i].u, G.edges[i].v);
				cost += G.edges[i].w;
			}
		}
		
		return cost;
	}

	//
	// Please, do not modify the read_and_solve method, as well as the main method
	//
	public static void read_and_solve(InputStream in, PrintStream out) {
		//
		// Define a scanner that will read the input
		//
		Scanner scanner = new Scanner(in);
		//
		// Read the number of test cases, and start executing
		//
		int T = scanner.nextInt();
		for (int test = 0; test < T; test += 1) {
			//
			// Read the number of vertices and edges
			//
			int V = scanner.nextInt();
			int E = scanner.nextInt();
			//
			// Create the graph representation
			// 
			Graph G = new Graph(V, E);
			//
			// Read all edges in the graph
			//
			for (int i = 0; i < E; i += 1) {				
				//
				// Read the vertices of the edge, as well as its
				// weight and add the edge in graph G.
				//
				int u = scanner.nextInt(); 
				int v = scanner.nextInt();
				int w = scanner.nextInt(); 				
				G.edges[i] = new Edge(u, v, w);
			}
			out.println(kruskal(G));
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