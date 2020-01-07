public class YannsAVLTree {



    public static void main(String[] args) {

    }
}
class Node{
    int key;
    int height;
    int size;
    Node left;
    Node right;
    Node(int key){
        this.key = key;
        height = 0;
        size = 1;
        left = null;
        right = null;
    }
}
