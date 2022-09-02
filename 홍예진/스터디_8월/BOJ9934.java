import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		final int MAX = (int) Math.pow(2, k);
		int[] tree = new int[MAX];
		int idx = (int) Math.pow(2, k - 1);
		for (int i = 1; i < MAX; i++) {
			tree[idx] = sc.nextInt();

			if (2 * idx + 1 < MAX && tree[2 * idx + 1] == 0) { // 우측 탐색을 해야하는 어떤 부모 노드다
				idx = 2 * idx + 1;
				while (idx * 2 < MAX) {// 해당 우측 트리의 가장 좌측 노드로 이동한다
					idx *= 2;
				}
			} else if (idx % 2 == 0) { // 하단 트리를 모두 지나온, 어떤 노드의 왼쪽 자식 노드다
				idx /= 2;
			} else { // 어떤 노드의 우측 자식 노드다
				while (tree[idx] != 0) // 탐색을 끝마친 트리의 부모까지 쭉 올라간다.
					idx /= 2;
			}
		}


		for (int i = 0; i < k; i++) {
			for (int j = 0; j < Math.pow(2, i); j++)
				System.out.print(tree[(int) (Math.pow(2, i) + j)] + " ");
			System.out.println();
		}
	}
}
