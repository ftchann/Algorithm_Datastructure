import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
    public static void main(String[] args) {
        Graph graph = new Graph(10 , 20);
        System.out.println(kruskal(graph));
    }

    public static class Edge implements Comparable<Edge> {
        public int u;    // vertex u of the edge
        public int v;    // vertex v of the edge
        public int w;    // the weight of the edge

        //
        // Edge initialization constructor
        //
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge other) {
            return w - other.w;
        }
    }

    static class Graph {
        public int V;            // number of vertices in the graph
        public int E;            // number of edges  in the graph
        public Edge[] edges;    // each edge in the graph

        //
        // Graph initialization constructor
        //
        public Graph(int V, int E) {
            this.V = V;
            this.E = E;
            this.edges = new Edge[E];
        }
    }

    public static class UnionFind {
        int[] size;
        int[] parent;

        //
        // The constructor, just calls `create`
        //
        public UnionFind(int n) {
            size = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                size[i] = 1;
                parent[i] = i;
            }
        }

        //
        // Initialize the union-data structure, by creating a
        // set for each element from [0, 1, ..., N - 1].
        //
        //
        // Determine which set a particular element belongs to.
        // Return the label or the 'id' of the set for a given x
        int find(int x) {
            // Path Compression
            int root = x;
            while (parent[root] != root) {
                root = parent[root];
            }
            int n = x;
            while (n != root) {
                int q = parent[n];
                parent[n] = root;
                n = q;
            }
            return root;
        }

        //
        // Connect or join two sets. In other words, change
        // the family by replacing two sets, the one containing
        // x and the one containing y, by a single set that is
        // the union of these two sets.
        //
        void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
            unionroots(xRoot, yRoot);
        }

        void unionroots(int xRoot, int yRoot) {
            if (xRoot == yRoot) {
                return;
            }
            if (size[xRoot] < size[yRoot]) {
                parent[xRoot] = yRoot;
                size[yRoot] += size[xRoot];
            } else {
                parent[yRoot] = xRoot;
                size[xRoot] += size[yRoot];
            }
        }
    }

    public static long kruskal(Graph G) {
        //
        // The cost of the MST will fit inside a long type.
        //
        long cost = 0;
        UnionFind uf = new UnionFind(G.V);
        Arrays.sort(G.edges);
        for (Edge edge : G.edges) {
            int u = edge.u;
            int v = edge.v;
            int a = uf.find(u);
            int b = uf.find(v);
            if (a != b) {
                uf.union(a, b);
                cost += edge.w;
            }
        }

        //
        // Complete the implementation of Kruskal's algorithm. Once you
        // compute the MST, compute the cost of the graph i.e. compute
        // the sum of the weights of all edges in the MST.
        //
        // Use the provided UnionFind data-structure available above.
        //

        return cost;
    }
}