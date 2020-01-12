import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Johnson {
    // JUST FUCK Johnson, I won't implement thit shit in the near future.
	
	//real talk, fuck johnson with a big wiener
    // The timecomplexity is still slower than floyd warshall cause it uses an Adjacency Matrix lul
    static class Pair implements Comparable<Pair>{
        int distance;
        int vertex;

        Pair(int distance, int vertex){
            this.distance = distance;
            this.vertex = vertex;
        }
        @Override
        public int compareTo(Pair other) {
            if(this.distance < other.distance) {
                return -1;
            }
            else if(this.distance == other.distance) {
                return 0;
            }
            return 1;
        }
    }
    public static void main(String[] args) throws Exception {
        int[][] d= {
                {0,-5,2,3},
                {Integer.MAX_VALUE,0,4,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,0,1},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0}
        };
        //Johnson;
        int[][] ans = Johnson(d);
        System.out.println(Arrays.deepToString(ans));
        System.out.println(Arrays.deepToString(FloydWarshall(d)));

    }
    public static int[][] Johnson(int[][] d) throws Exception {
        ArrayList<Edge> Edges = new ArrayList<>();
        int n=d.length;
        assert n==d[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j<n ;j++){
                if(d[i][j] != Integer.MAX_VALUE && i != j)
                    Edges.add(new Edge(i,j,d[i][j]));
            }
        }
        int[] h = BellmanFord(d, Edges);
        int[][] modifiedgraph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(d[i][j] == Integer.MAX_VALUE || i==j){
                    modifiedgraph[i][j] = d[i][j];
                }else{
                    modifiedgraph[i][j] = d[i][j] + h[i] - h[j];
                }
            }
        }
        /*
        System.out.println(Arrays.toString(h));
        System.out.println(Edges);
        System.out.println(Arrays.deepToString(modifiedgraph));
        */
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] temp = dijkstra(i,modifiedgraph);
            for (int j = 0; j < n; j++) {
                distance[i][j] = temp[j];
            }
        }
        //System.out.println(Arrays.deepToString(distance));
        // remodifie distances.
        int[][] distance2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance2[i][j] = distance[i][j] - (h[i] - h[j]);
            }
        }
        return distance2;
    }
    public static int[] dijkstra(int from, int[][] matrix) {
        int[] distance = new int[matrix.length];
        for (int i = 0; i < distance.length; i++) {
            distance[i]= 2147483647;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.add(new Pair(0, from));
        distance[from]=0;

        while(!pq.isEmpty()) {
            //System.out.println("here");
            Pair current = pq.remove();
            int dist = current.distance;
            int vertex = current.vertex;
            //System.out.println(dist);
            if(distance[vertex] < dist) {
                continue;
            }
            for(int i = 0; i<matrix.length; i++) { ;
                int next = i;
                int weight = matrix[vertex][i];
                //System.out.println(weight);
                if(weight == 2147483647) {
                    continue;
                }
                if(distance[next] <= dist+weight) {
                    continue;
                }
                distance[next] = dist + weight;
                pq.add(new Pair(dist+weight, next));
            }
        }
        return distance;
    }
    public static int[] BellmanFord(int[][] d, ArrayList<Edge> edges) throws Exception {
        int n = d.length;
        int[] dist = new int[n+1];
        dist[n] = 0;
        for (int i = 0; i < n; i++) {
            edges.add(new Edge(n,i,0));
        }
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n-1; i++) {
            for (Edge a: edges) {
                int v = a.to;
                int u = a.from;
                int w = a.weight;
                if(dist[u] !=  Integer.MAX_VALUE &&dist[v] > dist[u]+w){
                    dist[v] = dist[u] + w;
                }
            }
        }
        for (Edge a: edges) {
            int v = a.to;
            int u = a.from;
            int w = a.weight;
            if(dist[u] !=  Integer.MAX_VALUE && dist[v] > dist[u]+w){
                System.out.println("Negative Cycle");
                throw new Exception();
            }
        }
        int[] dist2 = new int[n];
        for (int i = 0; i < n; i++) {
            dist2[i] = dist[i];
        }
        return dist2;
    }
    public static int[][] FloydWarshall(int[][] d){
        int n=d.length;
        assert n==d[0].length;
        for (int i = 0; i < n; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if(d[u][i] == Integer.MAX_VALUE || d[i][v] == Integer.MAX_VALUE){
                        d[u][v] = d[u][v];
                    }else{
                        d[u][v] = Math.min(d[u][v],d[u][i] + d[i][v]);
                    }
                }
            }
        }
        return d;
    }
}
