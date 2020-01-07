

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

			Queue queue = new Queue();

			visited[s] = true;

			queue.enqueue(s);

			while (!queue.isEmpty()) {
				s = queue.dequeue();

				for (int i = 0; i < graph[s].length; ++i) {
					if (graph[s][i] == 1) {
						if (!visited[1]) {
							visited[i] = true;
							queue.enqueue(i);
						}
					}
				}
			}
		}
	}
	class Node {
		int key;
		Node next;
		
		Node(int key){
			this.key = key;
			this.next = null;
		}
	}
	
	class Queue {
		Node front;
		Node rear;
		
		Queue() {
			this.front = this.rear = null;
		}
		
		void enqueue(int key) {
			
			  // Create a new LL node 
			Node temp = new Node(key);
			
	        // If queue is empty, then new node is front and rear both 
	        if (this.rear == null) { 
	            this.front = this.rear = temp; 
	            return;   
	        } 
	        
	        // Add the new node at the end of queue and change rear 
	        this.rear.next = temp; 
	        this.rear = temp; 
	        
		}
		
		int dequeue () {
			
			// If queue is empty, return -1. 
	        if (this.front == null) 
	            return -1; 
	  
	        // Store previous front and move front one node ahead 
	        Node temp = this.front; 
	        this.front = this.front.next; 
	        
	        // If front becomes NULL, then change rear also as NULL 
	        if (this.front == null) 
	            this.rear = null; 
	        
	        return temp.key; 
		}
		
		boolean isEmpty() {
			return front == null;
		}
	}
}
