import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class PizzaProblem {
    public static void main(String[] args) throws FileNotFoundException {
        ReadAndWrite rw = new ReadAndWrite();
        File inFile = new File("a_example.in");
        File outFile = new File("a_example.out");
        rw.readAndSolve(new FileInputStream(inFile), new PrintStream(outFile));
    }
}


class ReadAndWrite {
    void readAndSolve(InputStream in, PrintStream out) {
        FastReader s = new FastReader(in);
        int M = s.nextInt();
        int N = s.nextInt();
        int[] S = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = s.nextInt();
        }
        boolean[][] dp = new boolean[N][M+1];
        dp[0][0] = true;
        dp[0][S[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M+1; j++) {
                if(dp[i-1][j])
                    dp[i][j] = true;
                if(j-S[i] >= 0 && dp[i-1][j-S[i]])
                    dp[i][j] = true;
            }
        }
        // find max
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            max = Math.max(i, max);
        }
        System.out.println(max);
        for (boolean[] x : dp)
        {
            for (boolean y : x)
            {
                if(y)
                    System.out.print(1 + " ");
                else
                    System.out.print(0 + " ");
            }
            System.out.println();
        }
        ArrayList<Integer> output = new ArrayList<>();
        int knum = N-1;
        int snum = max;
        while(knum > 0 && snum > 0){
            if(dp[knum][snum] == dp[knum-1][snum]){
                output.add(knum);
                knum = knum -1;
            }else{
                knum = knum -1;
                snum = snum - S[knum];
            }
        }
        if(snum != 0 )
            output.add(0);
        Collections.reverse(output);
        out.println(output.size());
        for (int a: output)
            out.print(a + " ");
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    FastReader(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    char nextChar() {
        return next().charAt(0);
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

