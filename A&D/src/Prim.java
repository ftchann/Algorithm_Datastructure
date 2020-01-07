import java.util.ArrayList;

public class Prim {
    public static void main(String[] args) {
        int[][] graph = new int[10][10]; //
        int n = graph.length;
        boolean[] visited = new boolean[n];
        PriorityQueue pq = new PriorityQueue();
        pq.add(new dEdge(0,0));
        int weight = 0;
        while (pq.heap.size() != 0){
            dEdge curr = pq.remove();
            int pos = curr.vertex;
            int dist = curr.weight;
            if(visited[pos]){
                continue;
            }
            visited[pos] = true;
            weight += dist;
            for (int i = 0; i < graph[pos].length; i++) {
                int next = graph[pos][i];
                int edge = 50; // get weight from graph. this graph doesn't support weight yet
                if(visited[next]){
                    continue;
                }
                pq.add(new dEdge(next, edge));
            }
        }
        System.out.println(weight);
    }
}
