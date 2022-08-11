import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Node root = new Node(sc.nextInt());
		while (sc.hasNext()) {
			int key = sc.nextInt();
			root.insert(new Node(key));
		}

		root.printPostOrder();
	}
}

class Node {
	int key;
	Node left, right;

	public Node(int key) {
		this.key = key;
	}

	public void insert(Node node) {
		if (this.key < node.key) {
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.insert(node);
			}
		} else {
			if (this.left == null) {
				this.left = node;
			} else {
				this.left.insert(node);
			}
		}
	}

	public void printPostOrder() {
		if (left != null)
			left.printPostOrder();
		if (right != null)
			right.printPostOrder();
		System.out.println(key);
	}
}
