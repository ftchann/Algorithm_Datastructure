public class LPS {
    // Longest palindromic substring, Manchesters algorithm will come later.
    // O(n^2) solution
    public static void main(String[] args) {
        String S = "GOKAYAKING";
        System.out.println(substringDP(S));
    }
    public static String substringDP(String S){
        int n = S.length();
        boolean[][] dp= new boolean[n][n];
        int maxlength = 1;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int start = 0;
        for (int i = 0; i < n-1; i++) {
            if(S.charAt(i) == S.charAt(i+1)){
                dp[i][i+1] = true;
                start = i;
                maxlength = 2;
            }
        }
        for (int k = 3; k <= n ; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k -1;
                if(dp[i+1][j-1] && S.charAt(i) == S.charAt(j)){
                    dp[i][j] = true;
                    start = i;
                    maxlength = k;
                }
            }
        }
        return S.substring(start, start+maxlength);
    }
}
