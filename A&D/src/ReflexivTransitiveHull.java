import java.util.Arrays;

public class ReflexivTransitiveHull {
    public static void main(String[] args) {
        int[][] matrix= {
                {0,1,0,0},
                {0,0,1,1},
                {1,0,0,0},
                {0,0,1,1}
        };
        System.out.println(Arrays.deepToString(matrix));
        //Calculate reflexiv transitve hull
        int n=matrix.length;
        assert n == matrix[0].length;
        for (int k = 0; k < n; k++) {
            matrix[k][k] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(matrix[i][k] == 1 && matrix[k][j] == 1 && matrix[i][j] == 0){
                        matrix[i][j] = 1;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }
}
