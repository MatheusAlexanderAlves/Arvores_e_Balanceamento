package arvorebalanceamento;

public class AVLTree {

	class Node {
		int key, height;
		Node left, right;

		Node(int key) {
			this.key = key;
			height = 1;
		}
	}

	private Node root;

	private int height(Node n) {
		return n == null ? 0 : n.height;
	}

	private int getBalance(Node n) {
		return n == null ? 0 : height(n.left) - height(n.right);
	}

	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		x.right = y;
		y.left = T2;

		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		return x;
	}

	private Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		y.left = x;
		x.right = T2;

		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		return y;
	}

	public void insert(int key) {
		root = insertRec(root, key);
	}

	private Node insertRec(Node node, int key) {
		if (node == null)
			return new Node(key);

		if (key < node.key)
			node.left = insertRec(node.left, key);
		else if (key > node.key)
			node.right = insertRec(node.right, key);

		node.height = 1 + Math.max(height(node.left), height(node.right));

		int balance = getBalance(node);

		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	// 🔥 ADICIONADO
	public void remove(int key) {
		root = removeRec(root, key);
	}

	private Node removeRec(Node node, int key) {
		if (node == null)
			return null;

		if (key < node.key)
			node.left = removeRec(node.left, key);
		else if (key > node.key)
			node.right = removeRec(node.right, key);
		else {
			if (node.left == null || node.right == null) {
				node = (node.left != null) ? node.left : node.right;
			} else {
				Node min = findMin(node.right);
				node.key = min.key;
				node.right = removeRec(node.right, min.key);
			}
		}

		if (node == null)
			return null;

		node.height = 1 + Math.max(height(node.left), height(node.right));

		int balance = getBalance(node);

		if (balance > 1 && getBalance(node.left) >= 0)
			return rightRotate(node);

		if (balance > 1 && getBalance(node.left) < 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balance < -1 && getBalance(node.right) <= 0)
			return leftRotate(node);

		if (balance < -1 && getBalance(node.right) > 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	private Node findMin(Node node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	public boolean search(int key) {
		return searchRec(root, key);
	}

	private boolean searchRec(Node node, int key) {
		if (node == null)
			return false;
		if (node.key == key)
			return true;

		return key < node.key ? searchRec(node.left, key) : searchRec(node.right, key);
	}

	public int height() {
		return height(root);
	}
}