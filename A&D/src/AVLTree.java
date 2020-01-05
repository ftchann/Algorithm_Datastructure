
class Node {
    int key;
    int height;
    Node left;
    Node right;

    Node(int d){
        key = d;
        height = 1;
    }
}

public class AVLTree {
    Node root;
    // getheight
    int height(Node n){
        if(n == null)
            return 0;
        return n.height;
    }
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    int getBalance(Node n){
        if(n == null)
            return 0;
        return height(n.left) - height(n.right);
    }
    Node rightRotate(Node z){
        Node y = z.left;
        Node T3 = y.right;
        // rotate
        y.right = z;
        z.left = T3;
        // update height
        y.height = max(height(y.left), height(y.right)) + 1;
        z.height = max(height(z.left), height(z.right)) + 1;
        return y;
    }
    Node leftRotate(Node z){
        Node y = z.right;
        Node T2 = y.left;
        // rotate
        y.left = z;
        z.right = T2;
        //update height
        y.height = max(height(y.left), height(y.right)) + 1;
        z.height = max(height(z.left), height(z.right)) + 1;
        return y;
    }
    Node insert(Node node, int key){
        if (node == null)
            return (new Node(key));
        if(key < node.key){
            node.left = insert(node.left, key);
        }
        else if(key > node.key){
            node.right = insert(node.right, key);
        }
        else // no double keys;
            return node;
        // update heights
        node.height = 1 + max(height(node.left), height(node.right));

        // rebalance

        int balance = getBalance(node);

        // left left case
        if(balance > 1 && key < node.left.key){
            return rightRotate(node);
        }
        // left right case;
        if(balance > 1 && key > node.left.key){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // right right case
        if(balance < -1 && key > node.right.key){
            return leftRotate(node);
        }
        // right left case
        if(balance < -1 && key < node.right.key){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    Node minvalueNode(Node node){
        Node current = node;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }
    Node deleteNode(Node root, int key){
        if(root == null){
            return root;
        }
        if(key < root.key){
            root.left = deleteNode(root.left , key);
        }
        else if(key > root.key){
            root.right = deleteNode(root.right, key);
        }
        // delete current node
        else {
            // node with one child or no child
            if(root.left == null || root.right == null){
                Node temp = null;
                if(temp == root.left){
                    temp = root.right;
                }
                else {
                    temp = root.left;
                }
                root = temp;
            }
            else {
                Node temp = minvalueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
        if(root == null)
            return root;
        root.height = max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.deleteNode(tree.root, 40);
        tree.root = tree.deleteNode(tree.root, 25);

        /* The constructed AVL Tree would be
             30
            /  \
          20   50
         /
        10
        */
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);
    }
}
