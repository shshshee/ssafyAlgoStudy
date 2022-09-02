import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution_1767_프로세서연결하기 {

	static class Core {
		int r, c; // core의 좌표

		public Core(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static char[][] map;
	static int res;
	static ArrayList<Core> posCore; // 좌표 넣기
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int[] orderIndex; // core를 어떤 순서로 전선 연결할 지
	static ArrayList<int[]> orders; // 안씀
	static boolean[] isSelected; // 순열 체크용

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			res = 0;
			posCore = new ArrayList<Core>(); // 좌표 초기화

			// 0. 맵 입력
			int N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for (int i = 0; i < map.length; i++) {
				String str = br.readLine();
				for (int j = 0, index = 0; j < map[i].length; j++, index += 2) {
					// core가 있는 자리 == '1', 없는 자리는 '0'
					map[i][j] = str.charAt(index);

					// 가장자리에 위치한 core는 이미 전원이 연결되어있다는 의미로 'c'로 표기
					if (i == 0 || i == map.length - 1 || j == 0 || j == map.length - 1) {
						map[i][j] = 'c';
					} else {
						// 가장자리에 있지 않은 Core의 위치 정보 넣기
						if (map[i][j] == '1')
							posCore.add(new Core(i, j));
					}
				}
			}

			// 1. 어떤 순서로 전선을 연결할 지 정하기 : 순열 완탐
			orderIndex = new int[posCore.size()]; // 순서
			isSelected = new boolean[posCore.size()]; // 순서
			orders = new ArrayList<>(); // 순서 모음집
			perm(0);

			// 정답 입력
			sb.append("#").append(t).append(" ").append(res).append("\n");
		} // end of test case

		// 정답 출력
		System.out.println(sb);
	}

	// 1. 순열
	static void perm(int cnt) {
		if (cnt == posCore.size()) {
			// 순열 완료

			System.out.println(Arrays.toString(orderIndex));
			for (int i = 0; i < orderIndex.length; i++) {
				Connect(posCore.get(orderIndex[i]).r, posCore.get(orderIndex[i]).c, 0, 0);
			}
			return;
		}
		for (int i = 0; i < posCore.size(); i++) {
			if (isSelected[i]) {
				continue;
			}
			orderIndex[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

	
	/*
	 * 돌면서 core를 만나면 상-하-좌-우 순서로 연결해보기
	 * - 연결된 core 개수 세기
	 * - 전선을 '2'로 표시 : 가는길에 '2'가 보이면 다른 방향으로 연결하기
	 * 
	 * 개수가 가장 많을 때의 길이 출력 -개수가 같으면 가장 짧은 길이로 출력
	 */
	static void Connect(int r, int c, int cnt, int length) { // r,c : core 좌표, cnt : 현재까지 연결한 core 개수
		if (cnt == posCore.size()) {
			res = length;
			return;
		}
		for (int d = 0; d < deltas.length; d++) {
			for(int index = 1; index <= map.length; index++) {
				int nr = r + deltas[d][0] * index;
				int nc = c + deltas[d][1] * index;
				
					
			}
		}
	}
}
