//NB: Fügen Sie keine Pakete hinzu.

//NB: Das Importieren von anderen Klassen ist NICHT ERLAUBT.
//NB: Das Benützen anderer Klassen, die nicht zu java.lang.* gehören, ist NICHT ERLAUBT.
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

// BITTE VERÄNDERN SIE DEN OBENSTEHENDEN CODE NICHT.


class Node
{
    public int key;
    public Node left = null;
    public Node right = null;

    public Node(int key)
    {
        this.key=key;
    }
}

//NB: Verändern Sie die untenstehende Deklaration der Klasse Main und der Methode main() NICHT,
//sonst kann der Judge das Programm nicht laufen lassen.
//Die Klasse MUSS als "class Main { ... }" deklariert werden,
//die Methode als "public static void main(String[] args) { ... }".
class Main
{

    static int index;
    //n bezeichnet die Anzahl Knoten des Binären Suchbaumes aus dem Input
    //Das i-te Element im Array keysPreorder (hat Index i-1 und) ist der Schlüssel (engl. key) des i-ten Knotens,
    //der durch eine Preorder Traversierung des Binären Suchbaumes aus dem Input besucht wird.
    static boolean LevelTraversal(Node root, int level, int[] ans){
        if(root == null){
            return false;
        }
        if(level == 1){
            //System.out.println(root.key);
            ans[index] = root.key;
            index++;
            return true;
        }
        boolean left = LevelTraversal(root.left, level -1, ans);
        boolean right = LevelTraversal(root.right, level-1, ans);
        return left || right;
    }

    static Node contructTree(int[] pre, int start, int end){
        if(start > end){
            return null;
        }
        Node root = new Node(pre[start]);
        int i;
        for(i = start;i<=end;i++){
            if(pre[i] > root.key){
                break;
            }
        }
        root.left=contructTree(pre, start+1, i-1);
        root.right=contructTree(pre, i, end);
        return root;
    }

    public static int[] solve(int n, int[] keysPreorder)
    {
        //TODO: Geben Sie einen Array aus n Ganzzahlen (engl. integers) zurück.
        //Das i-te Element dieses Arrays (hat Index i-1 und) soll gleich dem Schlüssel des des i-ten Knotens sein,
        //der durch eine Breitensuche auf dem Binären Suchbaum aus dem Input besucht wird.
        Node root = contructTree(keysPreorder, 0, n-1);
        int level=1;
        index = 0;
        int[] ans = new int[n];
        while (LevelTraversal(root, level, ans)){
            level++;
        }
        //System.out.println(Arrays.toString(ans));
        //System.out.println(root.key);
        return ans;
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
            int[] keysPreorder = new int[n];

            for(int i=0; i<n; i++)
                keysPreorder[i] = scanner.nextInt();

            int[] keysBFS = solve(n, keysPreorder);

            for(int i=0; i<n; i++)
            {
                if(i!=0)
                    output.print(" ");

                output.print(keysBFS[i]);
            }
            output.println();
        }

        scanner.close();
    }
}