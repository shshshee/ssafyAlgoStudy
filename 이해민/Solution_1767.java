import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1767 {
	
	static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	
	private static LinkedList<Pair> coreXY;
	private static int N;
	private static int result,maxCores, tempCnt;
	private static int[][] cells;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			
			cells = new int[N][N];
			coreXY = new LinkedList<Pair>();
			result = Integer.MAX_VALUE;
			maxCores = 0;
			tempCnt = 0;
			
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken());
					//가장 자리 제외 시키기
					if(i == 0 || j == 0 || i == N-1 || j == N-1) continue;
					if(cells[i][j] == 1) {
						coreXY.add(new Pair(i, j));
					}
				}
			}
			
			dfs(0, 0, 0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//상하좌우
	
	
	/**
	 * @param depth
	 * @param lines_cnt 라인 개수
	 * @param cores_cnt 코어 개수
	 */
	public static void dfs(int depth, int lines_cnt, int cores_cnt) {
		if(depth == coreXY.size()) {
			//결과 값 갱신
			if(maxCores < cores_cnt) {
				maxCores = cores_cnt;
				result = lines_cnt;	
			}else if(maxCores == cores_cnt) {
				result = Math.min(result, lines_cnt);
			}
			
			return;
		}
		
		
		int nowX = coreXY.get(depth).x;
		int nowY = coreXY.get(depth).y;
		
		for (int i = 0, size=dir.length; i < size; i++) {
			int nextX = nowX;
			int nextY = nowY;

			boolean flag = false;
		
			//이동할 수 있는 방향인지 확인 
			while(true) {
				nextX += dir[i][0];
				nextY += dir[i][1];
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) break;
				
				if(cells[nextX][nextY] == 1) {//전선은 절대로 교차해서는 안 됨
					flag = true;
					break;
				}
				
			}
			
			if(!flag) { 
				changeValue(nowX, nowY, i, 1);
				dfs(depth+1, lines_cnt + tempCnt, cores_cnt+1);
				changeValue(nowX, nowY, i, 0);
			}
		}
		
		dfs(depth+1, lines_cnt, cores_cnt);
		
		return;
	}
	
	public static void changeValue(int nowX, int nowY, int current, int value) {
		tempCnt = 0;
		int nextX = nowX + dir[current][0];
		int nextY = nowY + dir[current][1];
		while(true) {
			nextX += dir[current][0];
			nextY += dir[current][1];
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) break;
			cells[nextX][nextY] = value;
			tempCnt++;
		}
	}
	
	
}
