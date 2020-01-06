import testingshit.BFS.Stack;

//stack already defined in Stack class

public class BFS {

	class Graph {
		int[][] graph; // undirected graph represented by an adjacency matrix
		// feel free to add any other attributes
		boolean visited[];

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

		void BFS(int s) {

			Stack stack = new Stack();

			visited[s] = true;

			stack.push(s);

			while (!stack.isEmpty()) {
				s = stack.pop();

				for (int i = 0; i < graph[s].length; ++i) {
					if (graph[s][i] == 1) {
						if (!visited[1]) {
							visited[i] = true;
							stack.push(i);
						}
					}
				}
			}
		}

	
		
}
