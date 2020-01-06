// =======================================================================================================
// Test Exam    : WindT18.WindTurbines
// Author       :
// =======================================================================================================

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Main {

    //
    // Parameters include:
    //
    // n    - number of possible positions
    // D    - the minimal distance between two turbines
    // d[i] - position of the i-th turbine
    // e[i] - energy of the it-ht turbine
    //
    static int solve(int n, int D, int[] d, int[] e){
        //
        // Provide your solution here
        //
        int[] dp = new int[n+1];
        dp[1]=e[1];
        int lastpointer = 0;
        for (int i = 2; i <= n; i++) {
            for(;lastpointer < i; lastpointer++){
                if(lastpointer == 0){
                    continue;
                }
                if(d[i] - d[lastpointer] < D){
                    break;
                }
            }
            lastpointer--;
            if(lastpointer >= 0){
                dp[i]= max(dp[i-1], (dp[lastpointer] + e[i]));
            }
            else{
                dp[i]=max(dp[i-1], e[i]);
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    static int max(int a, int b){
        return a > b ? a: b;
    }


    public static void read_and_solve(InputStream in, PrintStream out)
    {
        Scanner scanner = new Scanner(in);

        int cases = scanner.nextInt();
        for (int t = 0; t < cases; t += 1) {
            //
            // Scan the input sizes i.e number of possible positions
            // as well as the minimal distance between two turbines
            //
            int n = scanner.nextInt();
            int D = scanner.nextInt();
            //
            // Allocate space for position & energy
            //
            int d[] = new int[n+1];
            int e[] = new int[n+1];
            //
            // Perform the scans for position and energy
            //
            for (int i = 1; i <= n; i += 1) d[i] = scanner.nextInt();
            for (int i = 1; i <= n; i += 1) e[i] = scanner.nextInt();

            out.println(solve(n, D, d, e));
        }

        scanner.close();
    }

    //
    // Do not modify the main method, and keep the method read_and_solve
    //
    public static void main(String[] args) {
        read_and_solve(System.in, System.out);
    }
}