// ==================================================================================================================
// Exercise   : AD19E01.Structure
// Submission : https://judge.inf.ethz.ch/team/websubmit.php?cid=28781&problem=AD19E01
// Author     :
// ==================================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
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
        public int compareTo(Edge other) {
            return w - other.w;
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
    // == THE IMPLEMENTATION OF THE UNION-FIND DATASTRUCTURE IS EFFICIENT   ===
    // ========================================================================
    //
    // Please modify the function such that both `union` and `find` operate in
    // O(log(n)) time. Feel free to add fields, and modify the existing methods.
    // Initialize the newly created fields in the constructor or the `create`
    // method if necessary.
    //
    public static class UnionFind {
        int[] parent;
        byte[] rank;
        //
        // The constructor, just calls `create`
        //
        public UnionFind (int N) {
            parent = new int[N];
            rank = new byte[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }
        //
        // Determine which set a particular element belongs to.
        // Return the label or the 'id' of the set for a given x
        //
        // Uses path compression to speed up future lookups.
        //
        int find (int x) {
            int root = x;
            // Find the root of the node by following the chain of pointers
            while (parent[root] != root) {
                root = parent[root];
            }
            // Path compression: Set the parent of the nodes that
            // are in the path to to the root to point directly to the root
            while (x != root) {
                int nextNode = parent[x];
                parent[x] = root;
                x = nextNode;
            }
            return root;
        }
        //
        // Connect or join two sets. In other words, change
        // the family by replacing two sets, the one containing
        // x and the one containing y, by a single set that is
        // the union of these two sets.
        //
        // Uses union by size to join the to sets which are
        // specified using the root. If you want to join arbitrary
        // sets use `uf.unionRoot(uf.find(a), uf.find(b)`;
        //
        void unionRoots (int xRoot, int yRoot) {
            if (rank[xRoot] < rank[yRoot])
                parent[xRoot] = yRoot;
            else if (rank[xRoot] > rank[yRoot])
                parent[yRoot] = xRoot;
            else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }
    }


    //
    // Calculate the minimum spanning tree (MST) using the Kruskal's algorithm
    // and return the cost of the graph
    //
    public static long kruskal (Graph G) {
        long cost = 0;
        UnionFind uf = new UnionFind(G.V);
        Arrays.sort(G.edges);
        for (Edge edge: G.edges) {
            int uRoot = uf.find(edge.u);
            int vRoot = uf.find(edge.v);
            if (uRoot != vRoot) {
                uf.unionRoots(uRoot, vRoot);
                cost += edge.w;
            }
        }
        return cost;
    }
    public static long boruvka (Graph G) {
        int N = G.V;
        int E = G.edges.length;

        int[] cheapest = new int[N];
        UnionFind uf = new UnionFind(N);
        int numTrees = N;
        long mstWeight = 0;
        Edge[] edges = G.edges;
        // Initialize to None
        for (int i = 0; i < N; i++) {
            cheapest[i] = -1;
        }
        // Cache of edges that are connecting nodes in the same connected component.
        boolean[] edgeIsUseless = new boolean[E];
        while (numTrees > 1) {
            for (int i = 0; i < E; i++) {
                if (edgeIsUseless[i]) continue;
                Edge edge = edges[i];
                int weight = edge.w;
                // Which connected component do these nodes belong to?
                int uRoot = uf.find(edge.u);
                int vRoot = uf.find(edge.v);
                // I don't care about an edge that connects nodes in the same connected component.
                if (uRoot == vRoot) {
                    edgeIsUseless[i] = true;
                    continue;
                }
                // Is this a better edge for the connected component that contains u?
                if (cheapest[uRoot] == -1 || edges[cheapest[uRoot]].w > weight) cheapest[uRoot] = i;
                // Is this a better edge for the connected component that contains v?
                if (cheapest[vRoot] == -1 || edges[cheapest[vRoot]].w > weight) cheapest[vRoot] = i;
            }
            for (int i = 0; i < N; i++) {
                int cheapestIndex = cheapest[i];
                // Reset for next iteration.
                cheapest[i] = -1;
                // -1 means that there is no cheapest edge.
                if (cheapestIndex == -1) continue;
                Edge cheap = edges[cheapestIndex];
                // Check if we didn't already connect the two components.
                int uRoot = uf.find(cheap.u);
                int vRoot = uf.find(cheap.v);
                if (uRoot == vRoot) {
                    edgeIsUseless[cheapestIndex] = true;
                    continue;
                }
                // This edge is part of the MST.
                uf.unionRoots(uRoot, vRoot);
                mstWeight += cheap.w;
                // We merge two trees
                numTrees--;
            }
        }
        return mstWeight;
    }
    /**
     * PriorityQueue class used by prim
     */
    public static class PQ {
        public static class Entry {
            int priority;
            int value;
            Entry(int priority, int value) {
                this.priority = priority;
                this.value = value;
            }
        }
        int[] priority;
        int[] value;
        int[] valuePos;
        int N;
        PQ(int capacity) {
            priority = new int[capacity];
            value = new int[capacity];
            valuePos = new int[capacity];
            N = 0;
        }
        PQ(int[] value, int[] priority, int[] valuePos) {
            this.value = value;
            this.priority = priority;
            this.valuePos = valuePos;
            N = value.length;
            this.buildHeap();
        }
        PQ(int[] startValues, int[] startPriority, int capacity) {
            priority = new int[capacity];
            value = new int[capacity];
            valuePos = new int[capacity];
            N = startValues.length;
            for (int i = 0; i < startValues.length; i++) {
                priority[i] = startPriority[i];
                value[i] = startValues[i];
                valuePos[startValues[i]] = i;
            }
            this.buildHeap();
        }

        private static int parentIndex(int index) {
            return ((index + 1)/2) - 1;
        }

        private static int leftIndex(int index) {
            return index * 2 + 1;
        }

        private static int rightIndex(int index) {
            return index * 2 + 2;
        }
        public void siftDown(int i) {
            int min = i;
            int l = leftIndex(i);
            int r = rightIndex(i);
            if (l < N && priority[l] < priority[min]) min = l;
            if (r < N && priority[r] < priority[min]) min = r;
            if (min == i) return;
            swap(i, min);
            siftDown(min);
        }
        public void siftUp(int index) {
            if(index <= 0) return;
            int parentIndex = parentIndex(index);
            if(priority[parentIndex] > priority[index]) {
                swap(index, parentIndex);
                siftUp(parentIndex);
            }
        }
        public void buildHeap () {
            int startIdx = (N / 2) - 1;
            for (int i = startIdx; i >= 0; i--) {
                siftDown(i);
            }
        }
        public Entry dequeue () {
            Entry min = new Entry(priority[0], value[0]);
            swap(0, N - 1);
            N--;
            siftDown(0);
            return min;
        }

        private void swap(int i, int j) {
            int a = value[j];
            value[j] = value[i];
            value[i] = a;

            int b = priority[j];
            priority[j] = priority[i];
            priority[i] = b;

            valuePos[value[i]] = i;
            valuePos[value[j]] = j;
        }

        public boolean isMinHeap() {
            for (int i = 0; i < N / 2; i++) {
                if (priority[i] > priority[leftIndex(i)])
                    return false;
                if (rightIndex(i) < N && priority[i] > priority[rightIndex(i)])
                    return false;
            }
            return true;
        }
    }
    private static class LinkedList {
        private static class Node {
            int weight;
            int vertex;
            Node next;
            Node(int vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
                next = null;
            }
        }
        Node first;
        LinkedList() {
            first = null;
        }
        public void add (int vertex, int weight) {
            Node oldFirst = first;
            first = new Node(vertex, weight);
            first.next = oldFirst;
        }
    }
    public static long prim (Graph G) {
        int V = G.V;
        int INF = Integer.MAX_VALUE;

        LinkedList[] adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList();
        }
        for (Edge e: G.edges) {
            adj[e.u].add(e.v, e.w);
            adj[e.v].add(e.u, e.w);
        }

        int[] value = new int[V];
        int[] priority = new int[V];
        int[] valuePos = new int[V];
        for (int i = 0; i < V; i++) {
            value[i] = i;
            valuePos[i] = i;
            priority[i] = INF;
        }
        priority[0] = 0;
        PQ q = new PQ(value, priority, valuePos);

        long mstWeight = 0;

        while (q.N > 0) {
            PQ.Entry min = q.dequeue();
            int vertex = min.value;

            mstWeight += min.priority;

            for(LinkedList.Node node = adj[vertex].first; node != null; node = node.next) {
                int ew = node.weight;
                int other = node.vertex;

                int heapPos = q.valuePos[other];
                // Stop if vertex is not part of the heap
                if (heapPos >= q.N) continue;
                if (q.priority[heapPos] < ew) continue;
                // Decrease priority
                q.priority[heapPos] = ew;
                q.siftUp(heapPos);
            }
        }
        return mstWeight;
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
            out.println(prim(G));
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