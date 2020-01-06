import java.util.Arrays;

public class Knapsack {
    public static void main(String[] args) {
        // dummy item for convenience
        // test 2 3 4
        int value[] = {0 ,1 ,4 ,5, 7};
        int weight[] = {0 ,1, 3, 4, 5};
        int maxweight = 7;
        assert value.length == weight.length;
        int n=value.length;
        int[][] dp = new int[n][maxweight+1];
        for (int i = 1; i < n; i++) {
            for (int w = 0; w < maxweight+1; w++) {
                int vi = value[i];
                int wi = weight[i];
                if(wi > w)
                    dp[i][w] = dp[i-1][w];
                else{
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-wi] + vi);
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[n-1][maxweight]);
    }
}
