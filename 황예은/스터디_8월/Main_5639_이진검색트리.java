package N5000;

import java.util.ArrayList;
import java.util.Scanner;

//
//class Node{
//	int value;
//	Node left;
//	Node right;
//	
//	public Node(int value) {
//		this.value = value;
//		left = null;
//		right = null;
//	}
//}
//
//class BinaryTree{
//	Node head;
//	int size;
//	
//	public BinaryTree() {
//		head = null;
//		size = 9;
//	}
//	
//}

public class Main_5639_이진검색트리 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> btree = new ArrayList();
		int root = sc.nextInt();
		btree.add(root);
		while(true) {
			if(sc.hasNext())
				btree.add(sc.nextInt());
		}
		
	}
}
