package swea.p5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 : SWEA 5648 원자 소멸 시뮬레이션
 * 출력 : 원자들이 소멸되면서 방출하는 에너지의 총합
 * */
public class Solution_5648 {
	
	private static int[][] delta = {{0,1}, {0,-1}, {-1,0}, {1,0}};//상하좌우
	private static int result;
	private static Queue<Point> queue;
	private static int[][] maps;//원자 위치
	
	static class Point {
		int x;
		int y;
		int dir;
		int energy;
		
		public Point(int x, int y, int dir, int energy) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
		
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", dir=" + dir + ", energy=" + energy + "]";
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());//원자의 개수
			
			queue = new LinkedList<Point>();
			
			maps = new int[4001][4001];
			result = 0;
			
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken()) + 1000;
				int y = Integer.parseInt(st.nextToken()) + 1000;
				int direction = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				queue.add(new Point(x*2, y*2, direction, k));
				maps[x*2][y*2] = k;
			}
		

					
			solve();
			
			System.out.printf("#%d %d\n", test_case, result);
		}//end of testcase
		

	}
	
	
	
	private static void solve() {
		
		while(true) {
			if(queue.size() <= 0) break;
			
			Queue<Point> temp = new LinkedList<Point>();
			for(Point now : queue) {
				
				int nowX = now.x;
				int nowY = now.y;
				int nowDir = now.dir;
				int nowEnergy = now.energy;
				
				maps[nowX][nowY] = 0;//지나가니까 0으로 
				
				int nextX = nowX + delta[nowDir][0];
				int nextY = nowY + delta[nowDir][1];
			
				if(nextX < 0 || nextX > 4000 || nextY < 0 || nextY > 4000) 
					continue;
				else 
					temp.add(new Point(nextX, nextY, nowDir, nowEnergy));
			}//for문 종료-> 1초 지남
		
			queue.clear();
			bomb(temp);
		}
		
	}
	
	//원자를 충돌시킴
	private static void bomb(Queue<Point> temp) {
		
		while(!temp.isEmpty()) {
			Point next = temp.poll();
			int nextX = next.x;
			int nextY = next.y;
			int nextDir = next.dir;
			int nextEnergy = next.energy;
						
			
			if(maps[nextX][nextY] == 0) {//충돌 x
				maps[nextX][nextY] = nextEnergy;
				queue.add(new Point(nextX, nextY, nextDir, nextEnergy));
			}else {//충돌 
				maps[nextX][nextY] = 0;
				result += nextEnergy;
			}
		}

	}
	
}




