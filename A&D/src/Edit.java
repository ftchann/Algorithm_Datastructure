import java.util.Arrays;

public class Edit {
    public static void main(String[] args) {
        String B = "Tiger";
        String A = "Ziege";
        A = " " + A;
        B = " " + B;
        int n= A.length();
        int m = B.length();
        int[][] E = new int[n][m];
        for (int i = 0; i < n; i++) {
            E[i][0] = i;
        }
        for (int i = 0; i < m; i++) {
            E[0][i] = i;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int deltaij = (A.charAt(i) == B.charAt(j)) ? 1 : 0;
                E[i][j] = min(E[i-1][j] + 1, E[i][j-1] + 1, E[i-1][j-1]+ 1-deltaij);
            }
        }
        //System.out.println(Arrays.deepToString(E));
        System.out.println(E[n-1][m-1]);
    }
    public static int min(int a, int b, int c){
        return Math.min(Math.min(a,b), c);
    }
}
