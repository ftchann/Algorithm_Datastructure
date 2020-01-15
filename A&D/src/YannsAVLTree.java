import java.util.*;
// Clean AVL Tree Implementatation with Rank.
//Please someone Test if it's correct. should work
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
		root.size = size(root.left) + size(root.right) + 1;
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
    public void remove(int key) {
    	root = remove(root, key);
    }
    private Node remove(Node root, int key) {
    	if(root == null) {
    		return null;
    	}
    	if(root.key < key) {
    		root.right = remove(root.right, key);
    	}else if(root.key > key) {
    		root.left = remove(root.left, key);
    	}else {
    		if(root.left == null) {
    			return root.right;
    		}
    		else if(root.right == null) {
    			return root.left;
    		}else {
    			int right_most_of_left = right_most(root.left);
    			root.left = remove(root.left, right_most_of_left);
    			root.key = right_most_of_left;
    		}
    	}
    	return balance(root);
    }
    private int right_most(Node root) {
    	while(root.right != null) {
    		root = root.right;
    	}
    	return root.key;
    }
    public List<Integer> list(int first_key, int last_key){
    	Node lca = _lca(first_key, last_key);
    	List<Integer> result = new ArrayList<Integer>();
    	_node_list(lca, first_key, last_key, result);
    	return result;
    }
    private Node _lca(int lo, int hi) {
    	Node node = root;
    	while(node != null && !(lo <= node.key && node.key <= hi)) {
    		if(lo < node.key) {
    			node = node.left;
    		}
    		else {
    			node = node.right;
    		}
    	}
    	return node;
    }
    private void _node_list(Node node , int lo, int hi, List<Integer> result){
    	if(node == null) {
    		return;
    	}
    	if(lo <= node.key && node.key <= hi) {
    		result.add(node.key);
    	}
    	if(node.key >= lo) {
    		_node_list(node.left, lo, hi , result);
    	}
    	if(node.key <= hi) {
    		_node_list(node.right, lo, hi, result);
    	}
    }
    public int count(int first_key, int last_key) {
    	int first_rank = rank(first_key);
    	int last_rank = rank(last_key);
    	int first_exist = exist(first_key);
    	return last_rank - first_rank + first_exist;
    }
    private int rank(int key) {
    	int rank = 0;
    	Node node = root;
    	while(node != null && !(node.key == key)) {
    		if(key < node.key) {
    			node = node.left;
    		}
    		else {
    			rank += size(node.left) + 1;
    			node = node.right;
    		}
    	}
    	if(node != null) {
    		rank += size(root.left) + 1;
    	}
    	return rank;
    }
    private int exist(int key) {
    	Node node = root;
    	while(node != null && !(node.key == key)) {
    		if(key < node.key) {
    			node = node.left;
    		}
    		else {
    			node = node.right;
    		}
    	}
    	if(node != null) {
    		return 1;
    	}
    	return 0;
    }
    private void _pre_(Node root, List<Integer> pre) {
    	if(root != null) {
    		pre.add(root.key);
        	_pre_(root.left, pre);
        	_pre_(root.right, pre);
    	}
    }
    // 0 based. the lowest Element is element 0;
	private int kele(int k, Node node){
		if(k > size(node)){
			throw new IllegalArgumentException();
		}
		if(size(node.left) == k){
			return node.key;
		}else if(size(node.left) > k){
			return kele(k, node.left);
		}else{
			//size node.left < k
			return kele(k-size(node.left) -1, node.right);
		}
	}

    public int ithelement(int i){
		int ans = kele(i, root);
		return ans;
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
    	mytree.add(0);
		System.out.println(mytree.ithelement(3));
    	System.out.println(mytree.pre_order());
    	System.out.println(mytree.list(1, 10));
    }
}

