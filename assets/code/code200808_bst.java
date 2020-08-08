package bst;

public class code200808_bst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree tree = new BinarySearchTree();

		System.out.println("====== insert node ======");
		tree.insert(50);
		tree.insert(30);
		tree.insert(20);
		tree.insert(40);
		tree.insert(70);
		tree.insert(60);
		tree.insert(80);

		tree.inOrder();
		System.out.println("====== delete node ======");
		tree.deleteKey(20); // delete leaf
		tree.deleteKey(30); // delete has only child
		tree.deleteKey(50); // delete has two children
		tree.inOrder();
		
		System.out.println("====== find node ======");
		System.out.println("find 70 : "+tree.find(70));
		System.out.println("find 30 : "+tree.find(30));
	}

}

class BinarySearchTree {
	class Node {
		int key;
		Node left;
		Node right;

		public Node(int value) {
			key = value;
			left = null;
			right = null;
		}

		public Node(int value, Node leftChild, Node rightChild) {
			super();
			this.key = value;
			this.left = leftChild;
			this.right = rightChild;
		}

		@Override
		public String toString() {
			return "[key=" + key + ", L=" + left.key + ", R=" + right.key + "]";
		}
		
	}

	Node root;

	public BinarySearchTree() {
		root = null;
	}

	public boolean find(int key) {
		return findRec(root, key);
	}

	private boolean findRec(Node curr, int key) {
		if(curr == null)
		return false;
		if(key < curr.key)
			return findRec(curr.left, key);
		else if(key > curr.key)
			return findRec(curr.right, key);
		else if(key== curr.key)
			return true;
		
		return false;
	}

	public void deleteKey(int key) {
		root = deleteRec(root, key);

	}

	private Node deleteRec(Node curr, int key) {
		if (curr == null)
			return curr;
		if (key < curr.key)
			curr.left = deleteRec(curr.left, key);
		else if (key > curr.key)
			curr.right = deleteRec(curr.right, key);
		else {
			// node with only child or null
			if (curr.left == null)
				return curr.right;
			else if (curr.right == null)
				return curr.left;

			// node with two children : get smallest value in right subtree
			curr.key = minValue(curr.right);
			curr.right = deleteRec(curr.right, curr.key);
		}
		return curr;
	}

	private int minValue(Node curr) {
		int minv = curr.key;
		while (curr.left != null) {
			minv = curr.left.key;
			curr = curr.left;
		}
		return minv;
	}

	public void inOrder() {
		inOrderRec(root);

	}

	private void inOrderRec(Node curr) {
		if (curr != null) {
			inOrderRec(curr.left);
			System.out.println(curr.key);
			inOrderRec(curr.right);
		}

	}

	void insert(int key) {
		root = insertRec(root, key);
	}

	Node insertRec(Node root, int key) {
		if (root == null) {
			root = new Node(key);
			return root;
		}

		if (key < root.key) {
			root.left = insertRec(root.left, key);
		} else if (key > root.key) {
			root.right = insertRec(root.right, key);
		}
		return root;

	}

}
