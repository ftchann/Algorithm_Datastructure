public class LinkedList {
    int size;
    Node first;
    Node last;
    public LinkedList(){
        size=0;
    }
    public void addlastValue(int value){
        Node n = new Node(value);
        if(size == 0){
            first = n;
            last = n;
        }
    }
    public static void main(String[] args) {
        LinkedList mylist = new LinkedList();
        mylist.addlastValue(5);
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