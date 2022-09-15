import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static int N, map[][], ans;
	static Point wormhall[];
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][] nextDir = { { 0, 1, 2, 3 }, { 2, 0, 3, 1 }, { 2, 3, 1, 0 }, { 1, 3, 0, 2 }, { 3, 2, 0, 1 },
			{ 2, 3, 0, 1 } };
	static boolean[][][] visited;

	public static Point getOppositePoint(int i, int j) {
		Point hall = wormhall[map[i][j] - 6];
		return new Point(hall.i - i, hall.j - j);
	}

	public static boolean OutBoundary(int i, int j) {
		return i < 0 || j < 0 || i >= N || j >= N;
	}

	public static int move(int i, int j, int dir, int point) {
		int ni = i + delta[dir][0];
		int nj = j + delta[dir][1];
		int nDir = dir;
		visited[dir][i][j] = true;

		while (true) {

			// 이동이 계속되는 경우
			// 1. 벽에 튕겨나올 때 (OutOfIndexException 방지를 위해 먼저 처리)
			if (OutBoundary(ni, nj) || map[ni][nj] == 5) {
				ni -= delta[nDir][0];
				nj -= delta[nDir][1];
				nDir = (nDir + 2) % 4; // 기존 방향의 반대 방향
				point++;
				continue;
			}

			visited[nDir][ni][nj] = true;

			// 이동이 끝나는 경우
			// 블랙홀을 만나거나, 출발선에 돌아왔을 때
			if (map[ni][nj] == -1 || (ni == i && nj == j)) {
				return point;
			}

			// 이동이 계속되는 경우
			// 2. 웜홀을 만난다.
			if (map[ni][nj] > 5) {
				Point p = getOppositePoint(ni, nj); // 반대편 좌표로 이동한다.
				ni = p.i;
				nj = p.j;
			} else { // 2. 빈곳이거나, 블록을 만나거나
				nDir = nextDir[map[ni][nj]][nDir]; // 나머지 만나 다음으로 이동했을 때 정해지는 방향
				// 블록은 점수를 1점 더 얻는다.
				if (map[ni][nj] != 0)
					point++;
			}

			ni += delta[nDir][0];
			nj += delta[nDir][1];

//			path.add(new Point(i, j, dir));
		}

	}

	public static void solve() {

		// 모든 좌표에 대하여 해당 방향으로 탐색을 시작한다. 해당 방향으로 이미 방문하였다면,좌표에 값을 저장하고 넘어간다.

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int dir = 0; dir < 4; dir++) {
					if (map[i][j] == 0 && !visited[dir][i][j]) {
						ans = Math.max(move(i, j, dir, 0), ans);
//						move(i, j, dir, 0); // i,j에서 dir 방향으로 출발해본다.
//						ans = Math.max(ans, visited[dir][i][j]);
					}
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ans = 0;
			wormhall = new Point[5]; // 6~10의 웜홀
			visited = new boolean[4][N][N]; // 각기 방향으로 지나간적이 있는지

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					if (num > 5) {
						int hallNum = num - 6;
						if (wormhall[hallNum] == null)
							wormhall[hallNum] = new Point(i, j);
						else {
							wormhall[hallNum].i += i;
							wormhall[hallNum].j += j;
						}
					}
				}
			}

			solve();

			System.out.print("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}
}

class Point {
	int i, j;
	int dir;

	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public Point(int i, int j, int dir) {
		this.i = i;
		this.j = j;
		this.dir = dir;
	}
}
