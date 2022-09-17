package swea.p5653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 : 5653. [모의 SW 역량테스트] 줄기세포배양
 * 문제 접근 방안 : bfs
 * 출력 : K시간 후 살아있는 줄기 세포(비활성 상태 + 활성 상태)의 총 개수
 * */
public class Solution_5653 {

	
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		int size;//생명력
		String type;//비활성, 활성
		
		public Pair(int x, int y, int size, String type) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.type = type;
		}

		@Override
		public int compareTo(Pair o) {
			return this.size-o.size;
		}
		
	}
	
	private static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//상하좌우
	private static PriorityQueue<Pair> queue;
	private static int count = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			count = 0;
			queue = new PriorityQueue<Pair>();
					
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp != 0) {
						queue.add(new Pair(i, j, temp, "inactive"));
					}
				}
			}
			changeActive(K);
		
			System.out.println("#"+test_case + " " + count);
		}
		
		br.close();
	}
	
	static boolean[][] visited = new boolean[1001][1001];
	static int[][] maps = new int[1001][1001]; 
	
	//비활성 상태->활성 상태->죽은 상태
	//활성상태->죽은상태에 대한 처리가 필요?? 
	//비활성상태인지 활성상태인지 구분 필요->type으로
	private static void changeActive(int K) {
		visited = new boolean[1001][1001];
		
		int time=1;
		while(time++ <= K) {
			
			Pair now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowSize = now.size;//생명력 수치
			String nowType = now.type;
			visited[nowX][nowY] = true;
			maps[nowX][nowY] = nowSize;
			
			if(nowSize <= time && nowType=="inactive") {//비활성 상태								
				queue.add(new Pair(nowX, nowY, nowSize, "active"));
			}
			
			for (int i = 0; i < dir.length; i++) {//4방향 탐색
				int nextX = nowX + dir[i][0];
				int nextY = nowY + dir[i][1];
				
				if(nextX < 0 || nextX >= 1001 || nextY < 0 || nextY >= 1001) break;
				
				if(!visited[nextX][nextY]) {
					if(nowType == "inactive" && maps[nextX][nextY] == 0) {
						queue.add(new Pair(nextX, nextY, nowSize, "inactive"));
					}
				}
			}

		}
		if(!queue.isEmpty()) {
			count += queue.size();
		}
		
	}


	
}
