import java.util.Arrays;
import java.util.Scanner;

// NB: For the judge to run the program, do not modify the declaration of the class Main or
// method main() below. The class has to be declared as "class Main { ... }"
// and the method as "public static void main(String[] args) { ... }"
class Main
{
	static int n; //the number of vertices in G
	static int[] outdegrees; //outdegrees[u] is the out-degree of vertex u in G
	static int[][] adjLists; //adjLists[u][j] is the j-th neighbor of vertex u in G (i.e., (u, adjLists[u][j]) is an edge in G)
	static int[] order;
	public static char solve()
	{
        //TODO: Solve the problem
	    int[] order = topologicalSort(adjLists);
	    boolean[] win = new boolean[n];
	    //System.out.println(Arrays.toString(order));
	    for (int i = 0; i < n/2; i++) {
	    	int temp = order[i];
	    	order[i] = order[n-i-1];
	    	order[n-i-1] = temp;
		}
	    for(int u: order) {
	    	boolean winning = false;
	    	for (int v = 0; v < adjLists[u].length; v++) {
				int neigh = adjLists[u][v];
				if(!win[neigh])
					winning = true;
			}
	    	win[u] = winning;
	    }
	    //System.out.println(Arrays.toString(order));
	    //DP
	    if(win[0])
	    	return 'A';
 		return 'B'; //Return either 'A' or 'B'
	}
	public static int[] topologicalSort(int[][] adj) {

	    // Setup
	    int n = adj.length;
	    boolean[] visited = new boolean[n];
	    int[] order = new int[n];
	    int index = n - 1;

	    // Visit each node
	    for (int u = 0; u < n; u++) {
	    	if (!visited[u]) {
	    		index = visit(adj, visited, order, index, u);
	    	}
	    }

	    // Return topological sort
	    return order;
	}
	private static int visit(int[][] adj, boolean[] visited, int[] order, int index, int u) {

	    if (visited[u]) return index;
	    visited[u] = true;

	    // Visit all neighbors
	    for (int v = 0; v < adj[u].length; v++) {
	    	if (!visited[adj[u][v]]) {
		    	  index = visit(adj, visited, order, index, adj[u][v]);
		      }
	    }
	      

	    // Place this node at the head of the list
	    //System.out.println(u);
	    order[index--] = u;

	    return index;
	  }
	
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int ntestcases = scanner.nextInt();
		
		for(int testno=0; testno<ntestcases; testno++)
		{			
			n = scanner.nextInt();
			outdegrees = new int[n];
			adjLists = new int[n][];
		
			for(int i=0; i<n; i++)
			{
				outdegrees[i] = scanner.nextInt();
				adjLists[i] = new int[outdegrees[i]];
				for(int j=0; j<outdegrees[i]; j++)
					adjLists[i][j] = scanner.nextInt();
			}
			
			System.out.println(solve());
		}
		
		scanner.close();
	}
}

