import java.util.Arrays;

public class SubsetSum {
    public static void main(String[] args) {
        // for convinience reason add 0 in front
        int[] A = {0 ,3, 2, 7 , 1};
        int sum = 6;
        int n = A.length;
        boolean[][] dp = new boolean[n][sum+1];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < sum+1; j++) {
                if(j == 0){
                    dp[i][j] = true;
                }else if(dp[i-1][j]){
                    dp[i][j] = true;
                } else if((j- A[i] >= 0) && dp[i-1][j-A[i]]){
                    dp[i][j] = true;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
    }
}