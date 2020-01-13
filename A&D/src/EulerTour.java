import java.util.ArrayList;
import java.util.LinkedList;
public class EulerTour {
    static class Edge{
        int u;
        int v;
        boolean marked;
        Edge(int u, int v){
            this.u = u;
            this.v = v;
            marked = false;
        }

        @Override
        public String toString() {
            return "{" + u +
                    "," + v +
                    '}';
        }
    }
    public static void main(String[] args) {
        //Annhame es existiert einer;
        //Create Graph;
        int V = 6;
        int E = 7;
        LinkedList<Edge>[] adj = new LinkedList[V];
        for (int i = 0; i < V; i += 1) {
            adj[i] = new LinkedList<Edge>();
        }
        int[] toadd = {0,1,0,2,1,2,1,4,4,5,1,3,3,5};
        for (int i = 0; i < E*2; i+=2) {
            int a = toadd[i];
            int b = toadd[i+1];
            Edge e = new Edge(a,b);
            adj[a].add(e);
            adj[b].add(e);
        }
        ArrayList<Edge> Z = new ArrayList<>();
        EulerWalk(adj, 0, Z);
        System.out.println(Z);
    }
    public static void EulerWalk(LinkedList<Edge>[] adj, int u, ArrayList<Edge> Z){
        for (Edge e: adj[u]) {
            if(!e.marked){
                e.marked = true;
                if(e.u != u){
                    EulerWalk(adj, e.u, Z);
                }else{
                    EulerWalk(adj, e.v, Z);
                }
                Z.add(e);
            }
        }
    }
}
