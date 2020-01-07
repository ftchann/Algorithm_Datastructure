import java.util.Arrays;

public class MaximumSubMatrix {
    public static void main(String[] args) {
        int[][] A = {{0, -2, -7, 0},
                     {9, 2, -6, 2},
                     {-4, 1, -4, 1},
                    {-1, 8, 0, -2}};
        System.out.println(maxSumRectangle(A));
    }
    private static int maxSumRectangle(int[][] M){
        int n = M.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0)
                    C[i][j] = M[i][j];
                else
                    C[i][j] = C[i-1][j] + M[i][j];
            }
        }
        System.out.println(Arrays.deepToString(C));
        int s = 0;
        int[] A = new int[n];
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                for (int j = 0; j < n; j++) {
                    if(a ==0){
                        A[j] = C[b][j];
                    }
                    else{
                        A[j] = C[b][j] - C[a-1][j];
                    }
                }
                s = Math.max(s, MaximumSubarraySum(A));
            }
        }
        return s;
    }
    public static int MaximumSubarraySum(int[] A){
        int maxsum=0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = Math.max(sum+A[i], 0);
            maxsum= Math.max(maxsum, sum);
        }
        return maxsum;
    }
}
