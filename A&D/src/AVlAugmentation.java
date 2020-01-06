//NB: KEIN Paket hinzufügen!
//NB: Es ist NICHT ERLAUBT, andere Klassen zu importieren.
//NB: Es ist NICHT ERLAUBT, andere Klassen die nicht zu java.lang.* gehören zu verwenden.

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

// BITTE VERÄNDERN SIE DIE ZEILEN OBERHALB NICHT!

class Node
{
    final int value; 		//The integer values stored in this node
    Node parent = null;		//Der Vorfahre (parent) dieses Knotens im AVL-Baum (oder NULL)
    Node leftChild = null;	//Der Linke Nachkomme (left child) dieses Knotens im AVL-Baum (oder NULL)
    Node rightChild = null;	//Der rechte Nachkomme (right child) dieses Knotens im AVL-Baum (oder NULL)
    int size = 1;

    int balanceFactor = 0;  //Differenz zwischen der Höhe des rechten Teilbaumes mit Wurzel rightChild und des linken Teilbaumes mit Wurzel leftChild

    Node(int v) //Konstruiert einen neuen Node
    {
        value=v;
    }
}

class AVLTree
{
    protected Node root = null;

    /** Links-Rotation mit Knoten @param pivot als Pivot **/
    private int getsize(Node u){
        if(u == null){
            return 0;
        }
        else{
            return u.size;
        }
    }
    private void updatesize(Node u){
        u.size = getsize(u.leftChild) + getsize(u.rightChild) + 1;
    }

    private void leftRotation(Node u)
    {
        Node v = u.rightChild; //Siehe Abbildung in der Problemstellung

        v.parent = u.parent;
        if(v.parent!=null)
        {
            if(v.parent.leftChild==u)
                v.parent.leftChild=v;
            else
                v.parent.rightChild=v;
        }
        else
            root = v;

        u.rightChild=v.leftChild;
        if(u.rightChild!=null)
            u.rightChild.parent=u;

        v.leftChild=u;
        u.parent=v;
        updatesize(u);
        updatesize(v);
    }

    /** Rechts-Rotation mit Knoten @param pivot als Pivot **/
    private void rightRotation(Node v)
    {
        Node u = v.leftChild; //Siehe Abbildung in der Problemstellung

        u.parent = v.parent;
        if(u.parent!=null)
        {
            if(u.parent.leftChild==v)
                u.parent.leftChild=u;
            else
                u.parent.rightChild=u;
        }
        else
            root = u;

        v.leftChild=u.rightChild;
        if(v.leftChild!=null)
            v.leftChild.parent=v;

        u.rightChild=v;
        v.parent=u;
        updatesize(v);
        updatesize(u);
    }

    /** Diese Funktion ruft die richtigen Rotationen auf, um alle unbalancierten Knoten auf dem Pfad von der
     * Wurzel bis zum Blatt @param leaf im aktuellen Baum
     auszubalancieren.
     **/
    //Es ist nicht nötig, den Code dieser Methode (rebalance) zu verändern, um die Aufgabe zu lösen (Es ist aber auch nicht verboten).
    private void rebalance(Node leaf)
    {
        int steps = 50; //Verhindert unendliche Loops, falls die Rotationen die Baumstruktur nicht korrekt verändern
        //Die Höhe eines Baumes ist kleiner als 50 für Bäume mit bis zu 12 Milliarden Knoten

        Node node = leaf.parent;  //Aktueller Knoten
        Node child = leaf; //Kind des Knotens auf dem Pfad zum Blatt
        for(;node!=null && steps>0;child=node,node=node.parent,steps--)
        {
            updatesize(node);
            if(node.rightChild==child)
            {
                if(node.balanceFactor==-1)
                {
                    node.balanceFactor=0;
                    break; //Höhe verändert sich nicht. Wir sind fertig.
                }

                if(node.balanceFactor==0)
                {
                    node.balanceFactor=1;
                    continue; //Höhe vergrössert sich
                }

                //Hier gilt node.balanceFactor==1. Der Knoten ist nach dem Einfügen vorübergehend unbalanciert.
                assert child.balanceFactor!=0;

                if(child.balanceFactor > 0) //Rechts-Rechts Fall
                {
                    leftRotation(node);
                    node.balanceFactor=0;
                    child.balanceFactor=0;
                    node = child;
                    break; //Höhe verändert sich nicht. Wir sind fertig.

                }

                //Rechts-Links Fall
                Node rlchild = child.leftChild;
                assert rlchild.balanceFactor!=0 || (rlchild.leftChild==null && rlchild.rightChild==null);

                rightRotation(child);
                leftRotation(node);

                if(rlchild.balanceFactor==0)
                {
                    rlchild.balanceFactor=0;
                    child.balanceFactor=0;
                    node.balanceFactor=0;
                    break; //Höhe verändert sich nicht. Wir sind fertig.

                }

                if(rlchild.balanceFactor==-1)
                {
                    rlchild.balanceFactor=0;
                    child.balanceFactor=1;
                    node.balanceFactor=0;
                    break; //Höhe verändert sich nicht. Wir sind fertig.

                }

                //Here rlchild.balanceFactor==1
                rlchild.balanceFactor=0;
                child.balanceFactor=0;
                node.balanceFactor=-1;
                break; //Höhe verändert sich nicht. Wir sind fertig.


            }
            else
            {
                if(node.balanceFactor==1)
                {
                    node.balanceFactor=0;
                    break; //Höhe verändert sich nicht. Wir sind fertig.

                }

                if(node.balanceFactor==0)
                {
                    node.balanceFactor=-1;
                    continue; //Höhe vergrössert sich
                }

                //Hier gilt node.balanceFactor==1. Der Knoten ist nach dem Einfügen vorübergehend unbalanciert.
                assert child.balanceFactor!=0;

                if(child.balanceFactor < 0) //Links-Links Fall
                {
                    rightRotation(node);
                    node.balanceFactor=0;
                    child.balanceFactor=0;
                    node = child;
                    break; //Höhe verändert sich nicht. Wir sind fertig.

                }

                //Links-Rechts Fall
                Node lrchild = child.rightChild;
                assert lrchild.balanceFactor!=0 || (lrchild.leftChild==null && lrchild.rightChild==null);

                leftRotation(child);
                rightRotation(node);

                if(lrchild.balanceFactor==0)
                {
                    lrchild.balanceFactor=0;
                    child.balanceFactor=0;
                    node.balanceFactor=0;
                    break; //Höhe verändert sich nicht.
                }

                if(lrchild.balanceFactor==1)
                {
                    lrchild.balanceFactor=0;
                    child.balanceFactor=-1;
                    node.balanceFactor=0;
                    break; //Höhe verändert sich nicht.
                }

                //Hier gilt rlchild.balanceFactor==-1
                lrchild.balanceFactor=0;
                child.balanceFactor=0;
                node.balanceFactor=1;
                break; //Höhe verändert sich nicht.

            }
        }

        if(steps==0)
            throw new RuntimeException("Diese Ausnahme verhinderte eine Endlosschleife");
    }

    /** Fügt ein Blatt mit Wert @param newValue an der korrekten Position in den Baum ein **/
    private Node addLeaf(int newValue)
    {
        //Baumtraversierung um den Vorgänger-Konten (Parent) für den neuen Wert zu finden
        Node parent = null;
        Node current = root;
        while(current!=null) //Suche nach dem Vorgänger-Knoten (Parent) des neuen Blatts
        {
            parent=current;

            if( newValue < current.value )
                current = current.leftChild;
            else
                current = current.rightChild;
        }

        //Neuen Knoten für den neuen Wert erstellen
        Node newLeaf = new Node(newValue);
        newLeaf.parent = parent;

        if(parent==null) //Falls der Baum leer war, wird der neue Knoten die Worzel des Baumes
            root = newLeaf;
        else if(newValue < parent.value)
            parent.leftChild = newLeaf;
        else
            parent.rightChild = newLeaf;

        return newLeaf;
    }

    public void updatepath(Node leaf){
        Node root = leaf;
        for(;root != null;root=root.parent){
            updatesize(root);
        }
    }

    /** Fügt den neuen Wert @param newValue an korrekter Position in den Baum ein **/
    public void insert(int newValue)
    {
        Node newLeaf = addLeaf(newValue);

        //Balanciert alle unbalancierten Knoten auf dem Pfad von der Wurzel zum Blatt aus.
        rebalance(newLeaf);
        updatepath(newLeaf);
    }

    /** Gibt die Anzahl Elemente zurück, die momentan um AVL-Baum gespeichert sind und die kleiner oder gleich @param x sind **/

    public int rank(int x)
    {
        int rang = 0;
        Node root = this.root;
        while(root != null && !(root.value == x)){
            if (x < root.value){
                root = root.leftChild;
            }
            else{
                rang += getsize(root.leftChild) + 1;
                root = root.rightChild;
            }
        }
        if(root != null){
            rang += getsize(root.leftChild) + 1;
            return rang;
        }
        return rang;

        //TODO: ERWEITERN SIE DEN AVL-BAUM UND IMPLEMENTIEREN SIE DIESE METHODE
    }

    /** Erstellt einen neuen AVL-Baum **/
    public AVLTree()
    {}
}

//Der folgende Code liest den Input ein, ruft die korrekten Operationen auf dem AVL-Baum auf und gibt den Output aus.
//Es wird dringend davon abgeraten, den untenstehenden Code zu verändern.

// NB: Bitte verändern Sie die untenstehenden Deklarationen der Klasse Main und der Methode main() NICHT, um das Programm auf dem Judge laufen zu lassen.
// Die Klasse muss als "class Main { ... }" deklariert sein.
// Die Methode muss als "public static void main(String[] args) { ... }" deklariert sein.

class Main
{
    public static void main(String[] args)
    {
        read_and_solve(System.in, System.out);
    }

    public static void read_and_solve(InputStream istream, PrintStream output)
    {
        Scanner scanner = new Scanner(istream);
        int num_testcases = scanner.nextInt();
        for(int i=0; i<num_testcases; i++)
        {
            AVLTree tree = new AVLTree(); //Erstellt einen neuen leeren AVL-Baum

            //Liest die Anzahl Operationen ein
            int num_operations = scanner.nextInt();
            for(int j=0; j<num_operations; j++)
            {
                String next_op = scanner.next();
                int x = scanner.nextInt();

                if(next_op.equals("I")) {
                    tree.insert(x);
                }
                else if(next_op.equals("R"))
                    output.println(tree.rank(x));
                else
                    throw new RuntimeException("Falsche Eingabe"); //Sollte nie passieren
            }
        }


        scanner.close();
    }
}