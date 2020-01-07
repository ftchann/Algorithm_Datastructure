
public class TopoSort {
    //Java PseudoCode i don't want to create tests. using tens stack hope he didn't fuck up.
    // Uses bad datastructures O(|V| * |E|) instead of O(|V| + |E|)
    public static void main(String[] args) {
        int[] V = {0,1,2,3,4,5,6,7,8,9};
        int n = 10; //Anzahl vertices
        Pair[] E = new Pair[10]; // Edges;
        int[] A = new int[10]; // Eingangsgrade der vertices;
        int[] ord = new int[10];
        Stack S= new Stack();
        for(Pair a:E){
            A[a.to]++;
        }
        for(int v: V){
            if(A[v] == 0){
                S.push(v);
            }
        }
        int i=1;
        while(!S.isEmpty()){
            int v = S.pop();
            ord[v] = i;
            i++;
            for (Pair a:E){
                if(a.from == v){
                    A[a.to]--;
                }
                if(A[a.to] == 0){
                    S.push(a.to);
                }
            }
        }
        if(i == n+1){
            System.out.println("Print that toposort!");
        }
        else{
            System.out.println("There is a cycle!");
        }
    }
}
class Pair{
    int from;
    int to;
    Pair(int from, int to){
        this.to = to;
        this.from = from;
    }
}