//stack already defined in Stack class

public class EulerpathExists {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int res = isEulerian();
        if (res==0)
          System.out.println("not eulerian");
        else if(res==1) 
        	 System.out.println("semi eulerian");
        else
        	 System.out.println("eulerian"); 
	}
	
	class Graph {
		int[][] graph; // undirected graph represented by an adjacency matrix
		// feel free to add any other attributes
		boolean[] visited;
		
		int [] degree;
		/**
		 * The class is instantiated once for every graph.
		 */
		Graph(int[][] g) {
			graph = g;
			// initialize other attributes, if you declared any
			for (int i = 0; i < g.length; ++i) {
				visited[i] = false;
			}
			
			for (int i = 0; i<graph.length; i++) {
				degree[i] = findDegree(i);
			}
		}

		public void DFS(int node) {

			visited[node] = true;

			for (int i = 0; i < graph[node].length; ++i) {
				if (graph[node][i] == 1) {
					if (visited[i] == false) {
						visited[i] = true;
						DFS(i);
					}
				}
			}
		}
		
		public boolean isConnected() {
			int nodes = graph.length;
			int i;
			for (i = 0; i < nodes; i++) {
				if (degree[i] != 0) {
					break;
				}
			}
			if (i == nodes) {
				return true;
			}
			DFS(i);
			
			 for (i=0; i < nodes; i++) {
		            if (visited[i] == false && degree[i] > 0)
		                return false;
		        }
			 
			return true;
		}
		
		
		public int isEulerian() {
			// TODO Auto-generated method stub
			  // Check if all non-zero degree vertices are connected 
	        if (isConnected() == false)
	            return 0;
	            
	        // Count vertices with odd degree 
	        int odd = 0;
	        for (int i = 0; i < graph.length; i++) {
	            if (degree[i] %2 != 0)
	                odd++;
	        }
	        
	        // If count is more than 2, then graph is not Eulerian 
	        if (odd > 2)
	            return 0;
	        
	        // If odd count is 2, then semi-eulerian. 
	        // If odd count is 0, then eulerian 
	        // Note that odd count can never be 1 for undirected graph 
	        
	        return (odd==2) ? 1 : 2;
		}
		
		int findDegree(int ver)  
	    { 
	        int degree = 0; 
	        for (int i = 0; i < graph.length; i++) { 
	            if (graph[ver][i] == 1) 
	                degree++; 
	        } 
	        return degree; 
	    } 
		
	}
}
