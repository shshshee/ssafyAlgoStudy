import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		while (T-- > 0) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			Queue<Integer> q = new LinkedList<Integer>();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
			for (int i = 0; i < n; i++) {
				int num = sc.nextInt();
				q.add(num);
				pq.add(num);
			}

			int count = 1;
			while (!q.isEmpty()) {
				int num = q.poll();
				if (num == pq.peek()) {
					pq.poll();

					// 찾는 정수가 나왔다면 순서를 저장하고 탐색을 그만한다.
					if (m == 0) {
						sb.append(count + "\n");
						break;
					}

					m--; // 그렇지 않다면 한칸 이동시킨다.
					count++;
				} else { // 현재 문서가 우선순위가 높지 않다면
					q.add(num); // 다시 맨 뒤에 넣는다.
					m = m - 1 < 0 ? q.size() - 1 : m - 1; // 내가 찾는 수도 이동한다.
				}
			}
		}
		System.out.println(sb);

	}
}
