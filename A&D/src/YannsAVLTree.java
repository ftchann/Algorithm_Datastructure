import java.util.*;
// Clean AVL Tree Implementatation with Rank;
public class YannsAVLTree {
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
	
	Node root;
	public YannsAVLTree() {
		root = null;
	}
    public void add(int key) {
    	root = insert(root, key);
    }
    private Node insert(Node root, int key) {
    	if(root == null) {
    		return new Node(key);
    	}
    	if(root.key < key) {
    		root.right = insert(root.right, key);
    	}
    	else {
    		root.left = insert(root.left, key);
    	}
    	return balance(root);
    }
    private void update_height(Node root) {
    	root.height=Math.max(height(root.left), height(root.right))+1;
    }
    private void update_size(Node root) {
    	root.size = size(root.left) + size(root.right);
    }
    private int height(Node root) {
    	if(root == null) 
    		return -1;
    	return root.height;
    }
    private int size(Node root) {
    	if(root == null)
    		return 0;
    	return root.size;
    }
    private Node balance(Node root) {
    	int left_height = height(root.left);
    	int right_height = height(root.right);
    	if(left_height >= right_height +2) {
    		if(height(root.left.left) > height(root.left.right)) {
    			root = right_rotate(root);
    		}
    		else {
    			root.left = left_rotate(root.left);
    			root = right_rotate(root.right);
    		}
    	}else if(right_height >= left_height +2) {
    		if(height(root.right.right) > height(root.right.left)) {
    			root = left_rotate(root);
    		}else {
    			root.right = right_rotate(root.right);
    			root = left_rotate(root);
    		}
    	}
    	else {
    		update_height(root);
    		update_size(root);
    	}
    	return root;
    }
    private Node left_rotate(Node root) {
    	Node right = root.right;
    	root.right = root.left;
    	right.left = root;
    	update_height(root);
    	update_height(right);
    	update_size(root);
    	update_size(right);
    	return right;
    }
    private Node right_rotate(Node root) {
    	Node left = root.left;
    	root.left = left.right;
    	left.right = root;
    	update_height(root);
    	update_height(left);
    	update_size(root);
    	update_size(left);
    	return left;
    }
    private void _pre_(Node root, List<Integer> pre) {
    	if(root != null) {
    		pre.add(root.key);
        	_pre_(root.left, pre);
        	_pre_(root.right, pre);
    	}
    }
    
    public List<Integer> pre_order() {
    	List<Integer> preorder = new ArrayList<Integer>();
    	_pre_(root, preorder);
    	return preorder;
    }

    public static void main(String[] args) {
    	YannsAVLTree mytree = new YannsAVLTree();
    	mytree.add(5);
    	mytree.add(3);
    	mytree.add(1);
    	System.out.println(mytree.pre_order());
    }
}

