public class Dijkstra {
    // Uses bad datastructure but i don't care
    public static void main(String[] args) {
        int[] V = {0,1,2,3,4,5,6,7,8,9};
        int n = V.length; //Anzahl vertices
        Edge[] E = new Edge[10]; // Edges;
        int d[] = new int[n];
        int p[] = new int[n];
        int s = 0; // Starting Vertex
        for (int i = 0; i < n; i++) {
            d[i] = Integer.MAX_VALUE;
            p[i] = -1; // -1 = null;
        }
        d[s] = 0;
        p[s] = -1;
        PriorityQueue Q = new PriorityQueue();
        while (Q.heap.size() != 0){
            dEdge curr = Q.remove();
            int u = curr.vertex;
            for(Edge e: E){
                if(e.from == u){
                    if(d[u] + e.weight < d[e.to] || p[e.to] == -1){
                        d[e.to] = d[u] + e.weight;
                        p[e.to] = u;
                        Q.add(new dEdge(e.to, d[e.to]));
                    }
                    // No idea how it should work with decrease key, how tf should i find (e.to = v) in my heap.?
                }
            }
        }
    }
}
