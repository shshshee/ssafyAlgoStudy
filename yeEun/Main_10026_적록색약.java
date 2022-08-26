
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_10026_적록색약 {
	static int N;
	static char[][] map;
	static char[][] mapEyes;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		mapEyes = new char[N][N];
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j);
				if (str.charAt(j) == 'G') {
					mapEyes[i][j] = 'R';
				} else {
					mapEyes[i][j] = str.charAt(j);
				}
			}
		}

		// [Debug] 출력
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map[i].length; j++) {
//				System.out.print(mapEyes[i][j]);
//			}
//			System.out.println();
//		}

		visited = new boolean[N][N];
		stk = new Stack<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				// 안가본곳 있으면 거기 돌기
				if (!visited[i][j])
					dfs(i, j, map);
			}
		}
		sb.append(stk.size()).append(" ");

		visited = new boolean[N][N];
		stk = new Stack<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!visited[i][j])
					dfs(i, j, mapEyes);
			}
		}
		sb.append(stk.size());
		System.out.println(sb);
	}

	static int[][] deltas = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }, };
	static int res;

	static Stack<Character> stk;

	static void dfs(int i, int j, char[][] map) {
		int cnt = 0, ncnt = 0;
		visited[i][j] = true; // 현재 위치는 방문

//		 새로운 영역이면 stack에 넣기

		if (stk.isEmpty() || map[i][j] != stk.peek()) {
			stk.push(map[i][j]);
//			System.out.println(map[i][j]);
		}

		// 주변 탐색
		for (int d = 0; d < deltas.length; d++) {
			int ni = i + deltas[d][0];
			int nj = j + deltas[d][0];
			cnt++;
			// 인덱스 범위 처리
			if (ni >= 0 && ni < map.length && nj >= 0 && nj < map.length) {
				// 탐색중심 기준으로 사방으로 탐색해서 안 가본 곳
				// && 탐색 중심이랑 같은 영역
				if (!visited[ni][nj] && map[ni][nj] == map[i][j]) {
//					System.out.println(map[i][j] + " == " + map[ni][nj]);
					visited[ni][nj] = true;
					dfs(ni, nj, map);
				}
//				// 주변 애들이 다 가봤던 곳이거나, 갈 수 있는 장소들이 전부 탐색 중심이랑 다른 영역이면
//				else {
//					ncnt++;
//				}
			}
		}
//		if (ncnt == cnt)
//			return;
	}

}
