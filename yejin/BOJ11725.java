import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		LinkedList<Integer>[] link = new LinkedList[n + 1];
		for (int i = 1; i <= n; i++)
			link[i] = new LinkedList<Integer>();

		for (int i = 0; i < n - 1; i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			link[n1].add(n2);
			link[n2].add(n1);
		}

		boolean visited[] = new boolean[n + 1];
		int[] parent = new int[n + 1];

		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		while(!q.isEmpty()) {
			int now = q.poll();
			visited[now] = true;
			for(int next  : link[now]) {
				if(!visited[next]) {
					parent[next] = now;
					q.add(next);
				}
			}
		}
		
		for(int i = 2; i <= n; i++)
			System.out.println(parent[i]);
		

	}
}
