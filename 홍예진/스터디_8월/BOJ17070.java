import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17070 {
	static int N, ans = 0;
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { 1, 1 } }; // 다음 이동 대상 단위 좌표
	static String[] conditions = { "100", "010", "111" }; // 비어져있어야하는 조건 표시
	// movable[i][j] : i상태에서 j상태로 움직이는게 가능한지 여부
	static boolean[][] movable = { { true, false, true }, { false, true, true }, { true, true, true } };
	static char[][] map;

	public static boolean OutBoundary(int i, int j) {
		return i < 0 || j < 0 || i >= N || j >= N;
	}

	public static boolean isEmpty(int i, int j, int next) {
		String condition = conditions[next];
		for (int r = 0; r < condition.length(); r++) {
			char ch = condition.charAt(r);
			if (ch == '1') {
				int ni = i + delta[r][0];
				int nj = j + delta[r][1];
				if (OutBoundary(ni, nj) || map[ni][nj] == '1') { // 맵위가 아니거나 벽으로 막혀있다면
					return false;
				}
			}
		}
		return true;
	}

	public static void solve(int i, int j, int state) {
	
		if (i == N - 1 && j == N - 1) {
			ans++;
			return;
		}

		for (int next = 0; next < 3; next++) {
			// 현재 상태에 대해, 움직이려는 방향(next state)의 조건에 맞으면 움직인다.
			if (movable[state][next] && isEmpty(i, j, next)) {
				solve(i + delta[next][0], j + delta[next][1], next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().replaceAll(" " , "").toCharArray();
		}
		

		solve(0, 1, 0);

		System.out.println(ans);
	}
}
