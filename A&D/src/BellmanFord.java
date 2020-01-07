public class BellmanFord {
    // Java pseudocode, I won't create Testdata and i won't read in input, feel free to do it yourself;
    // BellmanFord with unnormal datastructures for Graph
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
        // n-1 iterations of Bellman ford;
        for (int i = 0; i < n-1; i++) {
            for (Edge a: E) {
                int v = a.to;
                int u = a.from;
                int w = a.weight;
                if(d[v] > d[u]+w){
                    d[v] = d[u] + w;
                    p[v] = u;
                }
            }
        }
        for (Edge a: E) {
            int v = a.to;
            int u = a.from;
            int w = a.weight;
            if(d[v] > d[u]+w){
                System.out.println("Negative Cycle");
            }
        }
    }
}
class Edge{
    int from;
    int to;
    int weight;
    Edge(int from, int to, int weight){
        this.to = to;
        this.from = from;
        this.weight = weight;
    }
}
