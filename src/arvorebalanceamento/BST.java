package arvorebalanceamento;

public class BST {

	class Node {
		int key;
		Node left, right;

		Node(int key) {
			this.key = key;
		}
	}

	private Node root;

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

	// 🔥 ADICIONADO (OBRIGATÓRIO)
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
			if (node.left == null)
				return node.right;
			if (node.right == null)
				return node.left;

			Node min = findMin(node.right);
			node.key = min.key;
			node.right = removeRec(node.right, min.key);
		}

		return node;
	}

	private Node findMin(Node node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	public int height() {
		return heightRec(root);
	}

	private int heightRec(Node node) {
		if (node == null)
			return 0;
		return 1 + Math.max(heightRec(node.left), heightRec(node.right));
	}
}