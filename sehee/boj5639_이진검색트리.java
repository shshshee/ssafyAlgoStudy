
import java.io.*;
import java.util.*;

public class boj5639_이진검색트리 {
    // 트리를 전위순회한 결과가 입력으로 주어짐 -> 후위순회한 결과는 ?
    static class Node{
        int num;
        Node left, right;
        Node(int num){
            this.num = num;
        }
        void insert(int num){
            if(num < this.num){
                if(this.left == null){
                    this.left = new Node(num);
                }else{
                    this.left.insert(num);
                }
            }else{
                if(this.right == null){
                    this.right = new Node(num);
                }else{
                    this.right.insert(num);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        String input;
        while(true){
            input = br.readLine();
            if(input == null || input.equals("")) break;
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);

    }

    // 후위순회 (왼쪽 - 오른쪽 - 루트 )
    static void postOrder(Node node){
        if(node.left != null){
            postOrder(node.left);
        }

        if(node.right != null){
            postOrder(node.right);
        }

        System.out.println(node.num);
    }
}
