import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10026 {
	static int n, delta[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static char[][] map;
	static boolean[][] visited;

	public static boolean inBoundary(int i, int j) {
		return 0 <= i && 0 <= j && i < n && j < n;
	}

	public static void bfs(Point start) {
		char color = map[start.i][start.j];
		boolean isGreen = color == 'G';
		Queue<Point> q = new LinkedList<Point>();
		q.add(start);
		visited[start.i][start.j] = true;
		int qSize = q.size();
		while (!q.isEmpty()) {
			while (qSize-- > 0) {
				Point p = q.poll();
				if (isGreen)
					map[p.i][p.j] = 'R';

				for (int[] d : delta) {
					int i = p.i + d[0];
					int j = p.j + d[1];
					if (inBoundary(i, j) && !visited[i][j] && color == map[i][j]) {
						visited[i][j] = true;
						q.offer(new Point(i, j));
					}
				}
			}
			qSize = q.size();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					count++;
					bfs(new Point(i, j));
				}
			}
		}

		System.out.print(count + " ");

		visited = new boolean[n][n];
		count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					count++;
					bfs(new Point(i, j));
				}
			}
		}

		System.out.println(count);

	}

}

class Point {
	int i, j;

	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public String toString() {
		return "(" + i + ", " + j + ")";
	}
}
