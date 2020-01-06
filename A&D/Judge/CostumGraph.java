/**
 * Using this template, your task is to implement functions denoted by TODO in the comments.
 * 
 * Your task is to implement a directed graph representation, such that it supports the
 * following methods with given complexity.
 * 
 * <code>addEdge(int u, int v)</code> adds an edge from u to v in O(1).
 * <code>removeAllOut(int u)</code> removes all outgoing edges from u in O(out degree of u).
 * <code>removeAllIn(int v)</code> removes all incoming edges to v in O(in degree of v).
 * <code>isPath(int u, int v)</code> returns true if there exists a path from u to v.
 * Expected complexity is in O(|V| + |E|) (number of vertices plus number of edges).
 * Find the detailed description by the methods of class Graph.
 * 
 * You can define new classes and methods and extend the existing. Just do not change the API.
 * 
 * The Java project also contains tests, in Eclipse you can run them by left-clicking:
 * test/(default package)/JUnitTest.java and selecting Run as -> JUnitTest.java.
 * 
 * The tests will show you if they passed/failed. In case of fail, you can see an exception
 * if the code did not finished, or the difference between your output and expected output.
 * The test names should help you to explain what is being tested. You can also see the content
 * of the tests, for the format of the input, see <code>read</code> method.
 * 
 * The output format is described in the comment of <code>output</code> method.
 */

/**
 * This imports policy applies to all programming assignments and the exam.
 * 
 * You are forbidden to add any other import statements. Make sure that you do not leave any
 * imports commented out, or that your IDE does not add any automatically. You can recognize
 * allowed imports by the <code>// allowed import</code> comment.
 * Calls by the fully qualified name are treated as imports as well. Those are, for example,
 * <code>java.util.Arrays.sort</code>, or <code>java.util.Arrays.binarySearch</code>.
 * You can use data structures we provide to you by imports, by our definitions (even
 * implicitly used classes of packages, such as methods of Object those are inherited with
 * every class declaration, Array, or String), or data structures that you write on your own.
 * Usage of common arrays (<code>type[] variable</code>) is not restricted.
 * 
 * Note that Judge is not enforcing this policy. So if you violate this policy, you may lose the
 * bonus points even if Judge accepts your submission.
 * 
 * The general exceptions out of this policy is the package Math. The exceptions that are specific
 * to a given template are written down by its imports.
 */
import java.util.StringTokenizer; // allowed import
import java.io.BufferedReader; // allowed import
import java.io.IOException; // allowed import
import java.io.InputStream; // allowed import
import java.io.InputStreamReader; // allowed import
import java.io.PrintStream; // allowed import
import java.io.PrintWriter; // allowed import
import java.lang.Math; // allowed import
import java.util.ArrayList; // allowed import
import java.util.List; // allowed import
import java.util.LinkedList; // allowed import


class Main {
	public static void main(String[] args) {
		ReadAndWrite rw = new ReadAndWrite();
		rw.readAndSolve(System.in, System.out);
	}
}

class Graph {
	Vertex[] vertices; // directed graph vertices

	/**
	 * The class is instantiated once for every graph.
	 */
	Graph(int n) {
		vertices = new Vertex[n];
		
		for (int i = 0; i < n; ++i) {
			vertices[i] = new Vertex();
		}
	}
	
	/**
	 * TODO: Insert an edge (arc) from vertex u to vertex v.
	 * 
	 * Insert has to be in O(1) (it can be in average).
	 */
	void addEdge(int u, int v) {
		EdgePtr e = new EdgePtr(new Edge(u, v));
		
		vertices[u].out.add(e);
		vertices[v].in.add(e);
	}

	/**
	 * TODO: Remove all outgoing edges from vertex u.
	 * 
	 * Runtime has to be in O(outDegree(u)), where outDegree(u)
	 * means the number of outgoing edges from u.
	 */
	void removeAllOut(int u) {
		for (EdgePtr e : vertices[u].out) {
			e.e = null;
		}
		
		vertices[u].out.clear();
	}

	/**
	 * TODO: Remove all incoming edges from vertex v.
	 * 
	 * Runtime has to be in O(inDegree(v)), where outDegree(v)
	 * means the number of incoming edges from v.
	 */
	void removeAllIn(int v) {
		for (EdgePtr e : vertices[v].in) {
			e.e = null;
		}
		
		vertices[v].in.clear();
	}
	
	boolean isPathRec(int u, int v, boolean[] observed) {
		if (observed[u]) {
			return false; // skip it
		}
		if (u == v) {
			return true;
		}
		
		observed[u] = true;
		
		int counter = 0;
		boolean res = false;
		for (int i = 0; i < vertices[u].out.size(); ++i) {
			if (vertices[u].out.get(i).e != null) {
				if (!res) { // do not do DFS when we have the result
					// but do not return yet, we might have invalid state of the array
					res = res || isPathRec(vertices[u].out.get(i).e.out, v, observed);
				}
				vertices[u].out.set(counter++, vertices[u].out.get(i));
			}
		}
		while (counter != vertices[u].out.size()) {
			vertices[u].out.remove(vertices[u].out.size() - 1);
		}
		return res;
	}

	/**
	 * TODO: Test, if there is a path from vertex u to vertex v
	 * in the graph (this object).
	 * 
	 * Runtime has to be in O(V + E), where V is the number of vertices
	 * and E is the number of edges of this graph.
	 */
	boolean isPath(int u, int v) {
		boolean[] observed = new boolean[vertices.length];
		for (int i = 0; i < vertices.length; ++i) {
			observed[i] = false;
		}
		return isPathRec(u, v, observed);
	}

	/**
	 * This function is not tested and serves only for debugging.
	 * As the definition of the graph representation is up to you,
	 * so we cannot prepare the graph visualization for you.
	 * 
	 * Please implement it based on the commented pseudocode.
	 */
	void graphviz(PrintWriter writer) {
		for (Vertex v : vertices) {
			for (EdgePtr e : v.out) {
				if (e.e != null) {
					writer.write("\"" + e.e.in + "\" -> \"" + e.e.out + "\"\n");
				}
			}
		}
		/*
		writer.write("node [label=\"This graphviz graph is empty, as you have not implemented Graph.graphviz method];\"");
		for every_vertex {
			for every_edge {
				this code writes:
				"from_node" -> "to_node"
				writer.write("\"" + edge.from_node + "\" -> \"" + edge.to_node + "\"\n");
			}
		}
		*/
	}
}

class Vertex {
	ArrayList<EdgePtr> in;
	ArrayList<EdgePtr> out;
	
	public Vertex() {
		in = new ArrayList<EdgePtr>();
		out = new ArrayList<EdgePtr>();
	}
}

class EdgePtr {
	Edge e;
	public EdgePtr(Edge e) {
		this.e  = e;
	}
}

class Edge {
	int in;
	int out;
	
	public Edge(int i, int o) {
		in = i;
		out = o;
	}
}

///////////////////////////////////////////////////////////////////////
// DO NOT MODIFY THE FOLLOWING CODE, YOU CAN COMPLETELY IGNORE IT
// WE MAY USE LANGUAGE CONSTRUCTS THAT YOU MAY HAVE NOT SEEN SO FAR
///////////////////////////////////////////////////////////////////////

class ReadAndWrite {
	/**
	 * Parses input in form: <number of elements = n> 
	 * <n graphs in graph6 format>
	 * 
	 */
	void readAndSolve(InputStream in, PrintStream out) {
		FastReader s = new FastReader(in);

		int n = s.nextInt();
		
		for (int i = 0; i < n; i++) {
			int vertices = s.nextInt();
			int commands = s.nextInt();
			int u, v;
			
			Graph g = new Graph(vertices);
			
			for (int j = 0; j < commands; j++) {
				char command = s.nextChar();
				
				switch (command) {
				case 'a':
					u = s.nextInt();
					v = s.nextInt();
					g.addEdge(u, v);
					break;
				case 'o':
					u = s.nextInt();
					g.removeAllOut(u);
					break;
				case 'i':
					v = s.nextInt();
					g.removeAllIn(v);
					break;
				case 'p':
					u = s.nextInt();
					v = s.nextInt();
					out.println(g.isPath(u, v));
					break;

				default:
					System.err.println("Incorrect command" + command);
					break;
				}
				
			}
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
