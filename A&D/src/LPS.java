import java.util.Arrays;

public class LPS {
    // Longest palindromic substring, Manachers algorithm will come later.
    // O(n^2) solution
    public static void main(String[] args) {
        String S = "GOKAYAKING";
        String A = "BABA";
        System.out.println(manachersAlgorithm(A));
    }
    public static String manachersAlgorithm(String s){
        String toreturn = "";
        int N = s.length();
        String str = getModifiedString(s, N);
        int len = (2*N) + 1;
        assert len == str.length();
        int[] P = new int[len];
        int c = 0;
        int r = 0;
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            int mirror = (2 * c) -i;
            if(i<r){
                P[i] = Math.min(r-i,P[mirror]);
            }
            int a = i + (1 + P[i]);
            int b = i - (1 + P[i]);
            while(a<len &&  b>=0 && str.charAt(a) == str.charAt(b)){
                P[i]++;
                a++;
                b--;
            }
            if(i + P[i] > r){
                c = i;
                r = i + P[i];
                if(P[i] > maxLen){
                    maxLen = P[i];
                }
            }
        }
        for (int i = 0; i < len; i++) {
            if(P[i] == maxLen){
                toreturn = remodifie(str.substring(i-maxLen, i + maxLen));
            }
        }
        return toreturn;
    }
    public static String remodifie(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '#'){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public static String getModifiedString(String s, int N){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
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
