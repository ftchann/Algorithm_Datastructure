public class LinkedList {
    // Double Linked List
    int size;
    Node first;
    Node last;
    public LinkedList(){
        size=0;
    }
    public void print(){
        Node curr = first;
        while(curr != null){
            System.out.print(curr.value + " ");
            curr= curr.next;
        }
        System.out.println();
    }

    public void addlastValue(int value){
        Node n = new Node(value);
        if(size == 0){
            first = n;
            last = n;
        }
        else{
            last.next = n;
            n.prev= last;
            last = n;
        }
        size++;
    }
    public void addfirstValue(int value){
        Node n = new Node(value);
        if(size == 0){
            first = n;
            last = n;
        }
        else{
            first.prev = n;
            n.next = first;
            first = n;
        }
        size++;
    }
    public int removefirst(){
        // if size is zero then fuck you i don't throw errors.
        int val = first.value;
        if(size == 1){
            last = null;
            first = null;
        }
        else {
            Node second = first.next;
            first.next = null; // i hope thats how garbage collection works.
            second.prev = null;
            first = second;
        }
        size--;
        return val;
    }

    public static void main(String[] args) {
        LinkedList mylist = new LinkedList();
        mylist.addlastValue(5);
        mylist.addlastValue(4);
        mylist.addlastValue(6);
        mylist.addfirstValue(6);
        mylist.print();
    }
}
class Node{
    int value;
    Node prev;
    Node next;
    public Node(int value){
        this.value = value;
    }
}