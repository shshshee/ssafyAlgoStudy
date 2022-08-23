import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ9205 {
	static int n;

	public static void solve(boolean[] visited, Point[] points) {
		LinkedList<Point> queue = new LinkedList<>();
		queue.add(points[0]);
		int qSize = queue.size();
		while (!queue.isEmpty()) {
			while (qSize-- > 0) {
				Point p = queue.poll();

				if (isReachable(p, points[n + 1])) {
					System.out.println("happy");
					return;
				}

				visited[p.idx] = true;

				for (int i = 1; i < n + 1; i++) {
					if (!visited[i] && isReachable(p, points[i])) {
						queue.add(points[i]);
					}
				}

			}
			qSize = queue.size();
		}

		System.out.println("sad");
	}

	public static boolean isReachable(Point from, Point to) {
		return Math.abs(from.i - to.i) + Math.abs(from.j - to.j) <= 1000;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			Point[] points = new Point[n + 2];
			for (int idx = 0; idx < n + 2; idx++) {
				st = new StringTokenizer(br.readLine());
				short i = Short.parseShort(st.nextToken());
				short j = Short.parseShort(st.nextToken());
				points[idx] = new Point(idx, i, j);
			}

			solve(new boolean[n + 1], points);

		}

	}
}

class Point {
	int idx, i, j;

	public Point(int idx, int i, int j) {
		this.idx = idx;
		this.i = i;
		this.j = j;
	}

}
