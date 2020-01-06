//NB: KEIN Paket hinzufÃ¼gen!
//NB: Es ist NICHT ERLAUBT, andere Klassen zu importieren.
//NB: Es ist NICHT ERLAUBT, andere Klassen die nicht zu java.lang.* gehÃ¶ren zu verwenden.

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

// BITTE VERÃ„NDERN SIE DIE ZEILEN OBERHALB NICHT!

// NB: Bitte verÃ¤ndern Sie die untenstehenden Deklarationen der Klasse Main und der Methode main() NICHT, um das Programm auf dem Judge laufen zu lassen.
// Die Klasse muss als "class Main { ... }" deklariert sein.
// Die Methode muss als "public static void main(String[] args) { ... }" deklariert sein.

class Main
{
    //n ist die Anzahl Skulpturen in der Kunstgalerie
    //V ist die KapazitÃ¤t des Lastwagens
    //T ist die verbleibende Zeit bis zum eintreffen der Polizei
    //FÃ¼r i=1,...,n gilt: Die Elemente volume[i], time[i] und price[i] sind die ganzen Zahlen (Integer) v_i, t_i und p_i der Aufgabenstellung.
    //volume[0], time[0] und price[0] werden nicht benutzt und sind gleich 0.
    static int solve(int n, int V, int T, int[] volume, int[] time, int[] price)
    {
        int[][][] dp = new int[n+1][V+1][T+1];
        for(int i=1; i<=n; i++) {
            for(int j=0;j<=V;j++) {
                for(int k = 0; k<=T; k++) {
                    int a = 0;
                    int b = 0;
                    int c = 0;
                    int d = 0;

                    if(i-1 >= 1) {
                        a=dp[i-1][j][k];
                    }
                    if(j-1 >= 0) {
                        b=dp[i][j-1][k];
                    }
                    if(k-1>=0) {
                        c = dp[i][j][k-1];
                    }
                    if(j-volume[i] >= 0 && k-time[i] >= 0) {
                        d = dp[i-1][j-volume[i]][k-time[i]] + price[i];
                    }
                    dp[i][j][k] = max(a, b , c , d);
                }
            }
        }
        return dp[n][V][T]; //TODO: SCHREIBEN SIE IHRE LÃ–SUNG HIER.
    }

    public static void main(String[] args)
    {
        read_and_solve(System.in, System.out);
    }

    static int max(int a, int b, int c, int d) {
        return max(max(a, b), max(c, d));
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void read_and_solve(InputStream istream, PrintStream output)
    {
        Scanner scanner = new Scanner(istream);

        int ntestcases = scanner.nextInt();
        for(int t=0; t<ntestcases; t++)
        {
            int n = scanner.nextInt();
            int V = scanner.nextInt();
            int T = scanner.nextInt();

            int[] volume = new int[n+1];
            int[] time = new int[n+1];
            int[] price = new int[n+1];

            for(int i=1; i<=n; i++)
            {
                volume[i] = scanner.nextInt();
                time[i] = scanner.nextInt();
                price[i] = scanner.nextInt();
            }

            output.println(solve(n, V, T, volume, time, price));
        }

        scanner.close();
    }
}