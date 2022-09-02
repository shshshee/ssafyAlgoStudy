import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static int N, boundary[][], cNum, maxCount, minLengthSum;
	static Point[] points;
	static boolean[] visited;

	public static void checkNearest(int i, int j) {
		if (boundary[0][j] == 0) { // 상
			boundary[0][j] = cNum;
		}
		if (boundary[1][j] == 0) { // 좌
			boundary[1][j] = cNum;
		}
		if (boundary[2][i] == 0) { // 하
			boundary[2][i] = cNum;
		}
		if (boundary[3][i] == 0) { // 우
			boundary[3][i] = cNum;
		}
	}

	public static void solve(int cnt, int lengthSum, int lineCount) {
		if (cnt == cNum) {
			if (maxCount < lineCount) {
				maxCount = lineCount;
				minLengthSum = lengthSum;
			} else if (maxCount == lineCount) {
				minLengthSum = Math.min(minLengthSum, lengthSum);
			}
			return;
		}

		for (int i = 1; i < cNum; i++) {
			if(!visited[i]) {
				Point p = points[i];
				
				visited[i] = true;
				solve(cnt+1, lengthSum, lineCount);
				if (boundary[0][p.j] == 0) { // 위쪽을 연결할 수 있다. 
					// 위쪽으로 전선을 놓으면서 왼쪽 오른쪽의 가장 가까운 좌표를 갱신한다.
					// updateLR(p);
					solve(cnt+1, lengthSum+p.j, lineCount+1);
					// rollbackLR(p);
				}			
				
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			boundary = new int[4][N];
			cNum = 1;
			points = new Point[13]; // 코어의 개수는 최대 12개
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if (temp == 1) {
						checkNearest(i, j);
						// 가장자리 코어는 이미 연결된 것으로 간주한다.
						if (i == 0 || j == 0 || i == N - 1 || j == N - 1)
							continue;
						points[cNum++] = new Point(i, j);
					}
				}
			}
			visited = new boolean[cNum+1];

			solve(0, 0, 0);

			System.out.println("#" + tc + " ");
		}

	}
}

class Point {
	int i, j, cNum; // 코어의 고유 번호

	public Point(int i, int j, int cNum) {
		this.i = i;
		this.j = j;
		this.cNum = cNum;
	}

	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
