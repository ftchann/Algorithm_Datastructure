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
class Main {

	// n bezeichnet die Anzahl der ALGO Steine.
	// l[i] enthält die Länge des i-ten ALGO Steins, für i=1,...,n
	// b[i] enthält die Breite des i-ten ALGO Steins, für i=1,...,n
	// h[i] enthält die Höhe des i-ten ALGO Steins, für i=1,...,n
	static int solve(int n, int[] l, int[] b, int[] h) {
		for (int i = n; i >= 0; i--) {
			int maxH = 0;
			for (int j = i + 1; j <= n; j++) {
				if (i != 0 && !compatible(j, i, l, b))
					continue;
				maxH = h[j] > maxH ? h[j] : maxH;
			}
			h[i] += maxH;
		}
		return h[0];
	}

	public static boolean compatible(int i, int j, int[] l, int[] b) {
		return (l[j] >= l[i] && b[j] >= b[i]) || (l[j] >= b[i] && b[j] >= l[i]);
	}

	public static void main(String[] args) {
		read_and_solve(System.in, System.out);
	}

	public static void read_and_solve(InputStream istream, PrintStream output) {
		Scanner scanner = new Scanner(istream);

		int ntestcases = scanner.nextInt();
		for (int t = 0; t < ntestcases; t++) {
			int n = scanner.nextInt();

			int[] l = new int[n + 1];
			int[] b = new int[n + 1];
			int[] h = new int[n + 1];

			for (int i = 1; i <= n; i++)
				l[i] = scanner.nextInt();

			for (int i = 1; i <= n; i++)
				b[i] = scanner.nextInt();

			for (int i = 1; i <= n; i++)
				h[i] = scanner.nextInt();

			output.println(solve(n, l, b, h));
		}

		scanner.close();
	}
}