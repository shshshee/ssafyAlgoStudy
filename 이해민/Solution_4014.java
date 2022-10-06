package swea.p4014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014 {
	static int N;
	static int[][] maps;
	static int X;//경사로 길이 
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			maps = new int[N][N];
			result = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solve();
			sb.append(String.format("#%d %d\n", test_case, result));
		}
		System.out.println(sb);
	}
	

	static void solve() {
		//행
		for (int r = 0; r < N; r++) {
			boolean flag = false;
			for (int c = 0; c < N-1; c++) {
				int now = maps[r][c];
				int next = maps[r][c+1];
				
				if(now == next) continue;
				if(Math.abs(now - next) >= 2) continue;
				
				//경사로를 놓을 수 있는 지 확인
				int cnt;
				if(now > next) {	
					cnt = 0;
					for (int i = c+1; i < N; i++) {
						if(maps[r][i] != next) break;
						cnt++;
					}
				}else {
					cnt = 0;
					for (int i = c-1; i >= 0; i--) {
						if(maps[r][i] != now) break;
						cnt++;
					}
				}
				//경사로를 놓을 수 없는 경우
				if(cnt < X) flag = false;
				else flag = true;
			}
			if(flag) result++;
		}
		
		//열
		for (int c = 0; c < N; c++) {
			boolean flag = false;
			for (int r = 0; r < N-1; r++) {
				int now = maps[r][c];
				int next = maps[r+1][c];
				
				if(now == next) continue;
				if(Math.abs(now - next) >= 2) continue;
				
				//경사로를 놓을 수 있는 지 확인
				int cnt;
				if(now > next) {	
					cnt = 0;
					for (int i = r+1; i < N; i++) {
						if(maps[i][c] != next) break;
						cnt++;
					}
				}else {
					cnt = 0;
					for (int i = r-1; i >= 0; i--) {
						if(maps[i][c] != now) break;
						cnt++;
					}
				}
				//경사로를 놓을 수 없는 경우
				if(cnt < X) flag = false;
				else flag = true;
			}
			if(flag) result++;
		}
		
		
		
	}
}
/*
1
6 2
3 3 3 2 1 1
3 3 3 2 2 1
3 3 3 3 3 2
2 2 3 2 2 2
2 2 3 2 2 2
2 2 2 2 2 2
*/
