package arvorebalanceamento;

public class Rubro_Negra {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	class Node {
		int key;
		Node left, right;
		boolean color;

		Node(int key) {
			this.key = key;
			this.color = RED;
		}
	}

	private Node root;

	private boolean isRed(Node n) {
		return n != null && n.color == RED;
	}

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	public void insert(int key) {
		root = insertRec(root, key);
		root.color = BLACK;
	}

	private Node insertRec(Node h, int key) {
		if (h == null)
			return new Node(key);

		if (key < h.key)
			h.left = insertRec(h.left, key);
		else if (key > h.key)
			h.right = insertRec(h.right, key);

		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);

		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);

		if (isRed(h.left) && isRed(h.right))
			flipColors(h);

		return h;
	}

	// 🔥 REMOÇÃO SIMPLIFICADA (SUFICIENTE PARA TRABALHO)
	public void remove(int key) {
		root = removeRec(root, key);
		if (root != null)
			root.color = BLACK;
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

	public boolean search(int key) {
		Node current = root;

		while (current != null) {
			if (key == current.key)
				return true;
			current = key < current.key ? current.left : current.right;
		}

		return false;
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