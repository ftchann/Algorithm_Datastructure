

public class DFS {
	class Graph {
		int[][] graph; // undirected graph represented by an adjacency matrix
		// feel free to add any other attributes
		boolean[] visited;

		/**
		 * The class is instantiated once for every graph.
		 */
		Graph(int[][] g) {
			graph = g;
			// initialize other attributes, if you declared any
			visited = new boolean[g.length];
			
			for (int i = 0; i < g.length; ++i) {
				visited[i] = false;
			}
		}
		
		void DfsRecursive(int node) {
			
			
			visited[node] = true;

			for (int i = 0; i < graph[node].length; ++i) {
				if (graph[node][i] == 1) {
					if (visited[i]== false) {
						visited[i] = true;
						DfsRecursive(i);
					}
				}
			}
			
			
		}
		
		void DfsStack(int node) {
			Stack stack = new Stack();
			
			stack.push(node);
			
			while (!stack.isEmpty()) {
				node = stack.pop();
				
				visited[node] = true;
				
				for (int i = 0; i < graph[node].length; ++i) {
					if (graph[node][i] == 1) {
						if (visited[i]== false) {
							visited[i] = true;
							stack.push(i);
						}
					}
				}
				
			}
		}
	
	}

}
