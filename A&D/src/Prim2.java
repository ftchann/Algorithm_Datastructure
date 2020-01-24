import java.util.ArrayList;
import java.util.List;

class Vert implements Comparable<Vert> {
	int weight, index;

	public Vert(int weight, int index) {
		this.weight = weight;
		this.index = index;
	}

	public int compareTo(Vert e) {
		return weight - e.weight;
	}

}

public class Prim2 {
	public static int Prims(List<ArrayList<Vert>> adjList) {
		// Current cost of MST.
		int cost = 0;
		int n = adjList.size();

		PriorityQueue<Vert> pq = new PriorityQueue<Vert>();

		// Keep track if each node is visited.
		boolean visited[] = new boolean[n];
		for (int i = 0; i < n; i++) {
			visited[i] = false;
		}

		// Number of nodes visited.
		int inTree = 1;

		// Mark starting node as visited.
		visited[0] = true;

		// Add all edges of starting node.
		for (int i = 0; i < adjList.get(0).size(); i++) {
			pq.add(adjList.get(0).get(i));
		}
		// Keep going until all nodes visited.
		while (!pq.isEmpty() && inTree < n) {
			// Get the edge with the smallest weight.
			Vert cur = pq.poll();
			// Skip if node already used.
			if (visited[cur.index]) {
				continue;
			}
			inTree++;
			visited[cur.index] = true;
			cost += cur.weight;
			// Add all the edges of the new node to the priority queue.
			for (int i = 0; i < adjList.get(cur.index).size(); i++) {
				pq.add(adjList.get(cur.index).get(i));
			}
		}
		// Graph not connected if number of nodes used is less than total nodes.
		if (inTree < n) {
			return -1;
		}

		return cost;
	}
}