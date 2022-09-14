import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 문제 : 5656. [모의 SW 역량테스트] 벽돌 깨기
 * 접근 방안 : BFS + 중복조합
 * 			열 위치에 대해 경우의 수를 생각하고, 이를 토대로 bfs 탐색
 * */
public class Solution_5656 {
	
	private static int N;
	private static int[][] map;
	private static int H;
	private static int W;
	private static int[][] copyMap;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//쏠 수 있는 구슬 개수
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			result = Integer.MAX_VALUE;
			map = new int[H][W];
 			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			copyMap = new int[H][W];
			
			recoverArray();
			
			combination(new int[N], 0, 0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}//end of main
	
	//배열 복원
	private static void recoverArray() {
		for(int i = 0; i < H; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, copyMap[i].length);
		}		
	}
    
	//열 위치에 대해 경우의 수를 생각 --> 중복 조합
	//구슬을 쏠 열 위치 결정하는 메서드
	private static void combination(int[] output, int current, int count) {
		if(count == N) {
			for (int i = 0; i < output.length; i++) {
				bfs(output[i]);
			}
			result = Math.min(result, countBrick());
			return;
		}
		for (int idx = current; idx < W; idx++) {
			output[count] = idx;
			combination(output, idx, count+1);				
		}
	
		recoverArray();
	}
	
	
	private static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//상하좌우
	
	//벽돌깨기는 bfs
	private static void bfs(int col) {
		int row = searchPeek(col);//선택한 열에 대해 가장 윗행을 찾아야 함
				
		boolean[][] visited = new boolean[H][W];
		Queue<int[]> queue = new LinkedList<int[]>();		
		queue.add(new int[] {row, col});
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int nowX = temp[0];
			int nowY = temp[1];

			visited[nowX][nowY] = true;

			if(copyMap[nowX][nowY] == 1) {
				copyMap[nowX][nowY] = 0;
				continue;
			}
				
			for (int i = 0, size = dir.length; i < size; i++) {//상하좌우로 벽돌 깨기
				int cnt = copyMap[nowX][nowY]-1;
				nowX = temp[0];
				nowY = temp[1];
				while(cnt-- >= 1) {//갈 수 있는 깊이까지 벽돌을 부숨
					int nextX = nowX + dir[i][0];
					int nextY = nowY + dir[i][1];
						
					if(nextX < 0 || nextX >= H || nextY < 0 || nextY >= W) continue;
						
					if(visited[nextX][nextY]) continue;
						
					//벽돌 번호가 1보다 큰 경우 큐에 넣어줌
					if(copyMap[nextX][nextY] > 1) 
						queue.add(new int[] {nextX, nextY});				
					
					copyMap[nextX][nextY] = 0;
					nowX = nextX; nowY = nextY;
				}
			}				
			copyMap[nowX][nowY] = 0;
		}
		 
	}
	
	//선택한 열에 대해 가장 윗행을 찾는 메서드
	private static int searchPeek(int col) {
		int peek = 0;
		for (int i = 0; i < H; i++) {
			if(copyMap[i][col] != 0) {
				peek = i;
			}
		}
		return peek;
	}

	//벽돌을 내리는 작업을 수행
	private static void changeMap(int col) {
		//벽돌을 제거 후 벽돌을 내리기 위해서는 아래->위로 0이 아닌 숫자를 탐색하여 큐에 넣어줌
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < H; i++) {
			if(copyMap[i][col] != 0) {
				queue.offer(copyMap[i][col]);
			}
		}
		
		for (int i = 0; i < H; i++) {
			copyMap[i][col] = 0;
		}
	
		int row = H-1;
		//큐에 넣은 수를 그 열의 아래(H행)부터 쌓음
		while(!queue.isEmpty()) {
			int data = queue.poll();
			copyMap[row--][col] = data;
		}
	}
	
	//남은 벽돌 개수 카운트 메서드 
	private static int countBrick() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(copyMap[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
}//end of class
