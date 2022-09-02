
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];

		for (int i = 1; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// =====> end of 입력

		// 현재 파이프 상태
		int pipeStatus = 0; // 0(가로), 1(세로), 2(대각선)
		int pipeI = 1, pipeJ = 2; // 1, 2에 위치

		int subI = N - pipeI;
		int subJ = N - pipeJ;

		// 1. subI만큼 세로 이동 가능한 이동법 사용
		// index || moveVertical : 1 || moveDiagonal : 2

		// 2. subJ만큼 가로 이동 가능한 이동법 사용
		// index || moveWidth : 1 || moveDiagonal : 1

		// 3. 동시 이동
		// index || moveWidth : 2 || moveVertical : 2 || moveDiagonal : 3

		dfs();
	}

	// 변경될 상태, 끝쪽의 이동 좌표 i, j
	static int[][] moveWidth = { { 0, 0 }, { 0, 1 }, { 1, 1 } };
	static int[][] moveVertical = { { 0, 0 }, { 1, 0 }, { 1, 1 } };
	static int[][] moveDiagonal = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };

	private static void dfs() {
		
	}
}
