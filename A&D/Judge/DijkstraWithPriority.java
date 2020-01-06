
// you can import both java.util.Comparator as well as java.lang.Comparable for this task
import java.util.PriorityQueue; // allowed import
import java.util.StringTokenizer; // allowed import
import java.io.BufferedReader; // allowed import
import java.io.IOException; // allowed import
import java.io.InputStream; // allowed import
import java.io.InputStreamReader; // allowed import
import java.io.PrintStream; // allowed import
import java.lang.Math; // allowed import

class Main {
	public static void main(String[] args) {
		ReadAndWrite rw = new ReadAndWrite();
		rw.readAndSolve(System.in, System.out);
	}
}

class Vertex {
	int id;
	int weight;

	public Vertex(int id, int weight) {
		this.id = id;
		this.weight = weight;
	}
}

class PrioQ {
	Vertex[] queue;
	int currS;
	int targetS;

	public PrioQ(int targetS) {
		queue = new Vertex[targetS];
		this.currS = 0;
	}

	public boolean isEmpty() {
		boolean ret = (this.currS == 0) ? true : false;
		return ret;
	}

	public void swapVert(int i, int j) {
		Vertex curr = queue[i];
		queue[i] = queue[j];
		queue[j] = curr;
	}

	public void enqueue(Vertex v) {
		int vPlacement = this.currS;
		queue[vPlacement] = v;
		while (vPlacement >= 0 && (vPlacement - 1) / 2 >= 0 && v.weight < queue[(vPlacement - 1) / 2].weight) {
			swapVert(vPlacement, (vPlacement - 1) / 2);
			vPlacement = (vPlacement - 1) / 2;
		}
		this.currS++;
	}

	public Vertex dequeue() {
		Vertex ret = queue[0];
		swapVert(0, this.currS - 1);
		queue[this.currS - 1] = null;
		this.currS--;
		siftDown(0);
		return ret;
	}

	public void siftDown(int ind) {
		while (2 * (ind + 1) - 1 < this.currS) {
			if (queue[2 * (ind + 1) - 1].weight < queue[ind].weight) {
				swapVert(ind, 2 * (ind + 1) - 1);
				ind = 2 * (ind + 1) - 1;
			}
			if (2 * (ind + 1) < this.currS && queue[2 * (ind + 1)].weight < queue[ind].weight) {
				swapVert(ind, 2 * (ind + 1));
				ind = 2 * (ind + 1);
			} else {
				break;
			}
		}
	}
}

class Graph {
	int[][] matrix; // directed graph represented by a distance matrix
	// if you need, you can store other attributes
	// for each graph, method <code>dijkstra</code> is called just once

	/**
	 * The class is instantiated once for every graph.
	 * 
	 * Each graph contains 0 on the diagonal (distance u to u is 0) and
	 * <code>Integer.MAX_VALUE</code> states for a missing edge.
	 * 
	 * All edge lengths are positive.
	 */
	Graph(int n) {
		matrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
	}

	/**
	 * Inserts an edge from u to v with given weight.
	 */
	public void addEdge(int u, int v, int weight) {
		if ((u < 0 || u >= matrix.length) || (v < 0 || v >= matrix.length)) {
			return;
		}
		matrix[u][v] = weight;
	}

	/**
	 * TODO: Return an array of the shortest paths from vertex <code>from</code> to
	 * all vertices. That means that the the returned array will on position [i]
	 * contain distance from <code>from</code> to i.
	 * 
	 * If some vertex is not reachable, return <code>Integer.MAX_VALUE</code> for
	 * that vertex.
	 */
	int[] dijkstra(int from) {
		int[] ret = new int[matrix.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = Integer.MAX_VALUE;
		}
		ret[from] = 0;
		PrioQ q = new PrioQ(1000000);
		q.enqueue(new Vertex(from, 0));
		while (!q.isEmpty()) {
			Vertex v = q.dequeue();
			for (int i = 0; i < matrix.length; i++) {
				if (v.id != i && !(matrix[v.id][i] == Integer.MAX_VALUE)) {
//					System.out.println("x");
					if (ret[i] > v.weight + matrix[v.id][i]) {
						ret[i] = v.weight + matrix[v.id][i];
//						System.out.println("vw: " + v.weight + ", mat: " + matrix[v.id][i]);
//						System.out.print("retinp: " + (v.weight + matrix[v.id][i]));
//						System.out.println(" ");
						q.enqueue(new Vertex(i, v.weight + matrix[v.id][i]));
					}
				}
//				else {
//					System.out.println("y");
//				}
			}
//			System.out.println("neu");
		}
		return ret;
	}
}

///////////////////////////////////////////////////////////////////////
// DO NOT MODIFY THE FOLLOWING CODE, YOU CAN COMPLETELY IGNORE IT
// WE MAY USE LANGUAGE CONSTRUCTS THAT YOU MAY HAVE NOT SEEN SO FAR
///////////////////////////////////////////////////////////////////////

class ReadAndWrite {
	/**
	 * Parses input of n distance matrices
	 */
	void readAndSolve(InputStream in, PrintStream out) {
		FastReader s = new FastReader(in);

		int n = s.nextInt();

		for (int i = 0; i < n; i++) {
			int vertices = s.nextInt();

			Graph g = new Graph(vertices);

			int w;
			for (int u = 0; u < vertices; u++) {
				for (int v = 0; v < vertices; v++) {
					w = s.nextInt();
					if (w != -1) {
						g.addEdge(u, v, w);
					}
				}
			}

			out.println(java.util.Arrays.toString(g.dijkstra(0)));
		}
	}
}

/**
 * Ignore this class please. It is used for input parsing. Source:
 * https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastReader {
	BufferedReader br;
	StringTokenizer st;

	FastReader(InputStream in) {
		br = new BufferedReader(new InputStreamReader(in));
	}

	String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	char nextChar() {
		return next().charAt(0);
	}

	String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}