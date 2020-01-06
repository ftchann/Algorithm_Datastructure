//NB: Fügen Sie keine Pakete hinzu.

//NB: Das Importieren von anderen Klassen ist NICHT ERLAUBT.
//NB: Das Benützen anderer Klassen, die nicht zu java.lang.* gehören, ist NICHT ERLAUBT.
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

// BITTE VERÄNDERN SIE DEN OBENSTEHENDEN CODE NICHT.

//NB: Verändern Sie die untenstehende Deklaration der Klasse Main und der Methode main() NICHT,
//sonst kann der Judge das Programm nicht laufen lassen.
//Die Klasse MUSS als "class Main { ... }" deklariert werden,
//die Methode als "public static void main(String[] args) { ... }".
class Main
{

    //n bezeichnet die Anzahl der ALGO Steine.
    //l[i] enthält die Länge des i-ten ALGO Steins, für i=1,...,n
    //b[i] enthält die Breite des i-ten ALGO Steins, für i=1,...,n
    //h[i] enthält die Höhe des i-ten ALGO Steins, für i=1,...,n
    static int solve(int n, int[] l, int[] b, int[] h)
    {
        //TODO: Geben Sie die Höhe des höchsten ALGO Turms, der aus den vorhandenen Steinen
        //gebaut werden kann, zurück.
        //
        int currentIndex = n+1;
        int randomIndex = n+1;
        int temporaryValue = n+1;
        while (0 != currentIndex) {

            // Pick a remaining element...
            randomIndex = (int)Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;
            if(randomIndex == 0)
                continue;
            // And swap it with the current element.
            temporaryValue = l[currentIndex];
            l[currentIndex] = l[randomIndex];
            l[randomIndex] = temporaryValue;

            temporaryValue = h[currentIndex];
            h[currentIndex] = h[randomIndex];
            h[randomIndex] = temporaryValue;

            temporaryValue = b[currentIndex];
            b[currentIndex] = b[randomIndex];
            b[randomIndex] = temporaryValue;
        }

        int []memtable = new int[n+1];
        for (int i = 0; i < memtable.length; i++) {
            memtable[i] = -1;
        }
        int max = 0;

        for (int i = n; i >= 1; i--) {
            max = Math.max(rec(n, l ,b, h, i, memtable), max);
        }


        //rec(n, l ,b, h, 1, memtable);
        //System.out.println(max);
        return max;
    }

    static int rec(int n, int[] l, int[] b, int[] h,int i, int[] memtable){
        if(memtable[i] != -1){
            return memtable[i];
        }
        memtable[i] = h[i];
        int mini = Math.min(b[i], l[i]);
        int maxi = Math.max(b[i], l[i]);
        int maxtoadd = 0;
        for (int j = 1; j <= n; j++) {
            if(j == i){
                continue;
            }
            int minj = Math.min(b[j], l[j]);
            int maxj = Math.max(b[j], l[j]);
            if(minj <= mini && maxj <= maxi){
                maxtoadd = Math.max(rec(n, l, b, h, j, memtable), maxtoadd);
            }
        }
        memtable[i] += maxtoadd;
        return memtable[i];
    }

    public static void main(String[] args)
    {
        read_and_solve(System.in, System.out);
    }

    public static void read_and_solve(InputStream istream, PrintStream output)
    {
        Scanner scanner = new Scanner(istream);

        int ntestcases = scanner.nextInt();
        for(int t=0; t<ntestcases; t++)
        {
            int n = scanner.nextInt();

            int[] l = new int[n+1];
            int[] b = new int[n+1];
            int[] h = new int[n+1];

            for(int i=1; i<=n; i++)
                l[i] = scanner.nextInt();

            for(int i=1; i<=n; i++)
                b[i] = scanner.nextInt();

            for(int i=1; i<=n; i++)
                h[i] = scanner.nextInt();

            output.println(solve(n, l, b, h));
        }

        scanner.close();
    }
}