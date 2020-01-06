import java.util.Arrays;

public class LCS {
    public static void main(String[] args) {
        String A = "Tiger";
        String B = "Ziege";
        A = " " + A;
        B = " " + B;
        int n= A.length();
        int m = B.length();
        int[][] L = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int deltaij = (A.charAt(i) == B.charAt(j)) ? 1 : 0;
                L[i][j] = Math.max(L[i-1][j-1]+deltaij, Math.max(L[i][j-1],L[i-1][j]));
            }
        }
        //System.out.println(Arrays.deepToString(L));
        System.out.println(L[n-1][m-1]);
    }
}
