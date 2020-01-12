import java.util.Arrays;

public class FloydWarshall {
    public static void main(String[] args) {
        // distances from one vertex to another in a weighted, directed graph;
        int[][] d= {
                {0,-5,2,3},
                {Integer.MAX_VALUE,0,4,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,0,1},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0}
        };
        int[][] a ={{0,-2,-3},
                {4,0,0},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
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
        System.out.println(Arrays.deepToString(d));
        //Check for negative Cycle
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < u; v++) {
                if(d[u][v] < 0){
                    System.out.println("Negative Cycle");
                }
            }
        }
    }
}
