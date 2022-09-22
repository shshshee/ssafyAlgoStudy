package swea.p5648;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 : SWEA 5648 원자 소멸 시뮬레이션
 * 출력 : 원자들이 소멸되면서 방출하는 에너지의 총합
 * */
public class Solution_5648 {
	
	private static int[][] delta = {{0,1}, {0,-1}, {-1,0}, {1,0}};//상하좌우
	private static int result;
	private static List<Pair> lists;
	
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		int dir;
		int energy;
		
		public Pair(int x, int y, int dir, int energy) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
		
		@Override
		public int compareTo(Pair o) {
			if(this.x == o.x && this.y == o.y) return this.energy - o.energy;
			return this.x - o.x;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());//원자의 개수
			
			lists = new LinkedList<Pair>();
			
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				//좌표를 1000 <= x,y <= 2000으로 바꾸어 줌 
				int x = Integer.parseInt(st.nextToken()) + 1000;
				int y = Integer.parseInt(st.nextToken()) + 1000;
				int direction = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				lists.add(new Pair(x, y, direction, k));
			}
			solve();
			
			bw.write("#" + test_case + " ");
			bw.write(result);
			bw.newLine();
		}//end of testcase
		
		bw.flush();
		bw.close();
	}
	
	
	

	//모든 원자 이동 시킨 후 충돌시키기
	private static void solve() {
		
		while(true) {
			if(lists.size() <= 0) break;
			
			List<Pair> temps = new LinkedList<Pair>();
			for(Pair now : lists) {
				
				int nowX = now.x;
				int nowY = now.y;
				int nowDir = now.dir;
				int nowEnergy = now.energy;
				
				int nextX = nowX + delta[nowDir][0];
				int nextY = nowY + delta[nowDir][1];
				
				if(nextX < 2000 || nextY >= 4000 || nextY < 2000 || nextY >= 4000) continue;
				
				temps.add(new Pair(nextX, nextY, nowDir, nowEnergy));
			}
			
			lists.clear();
			lists = bomb(temps);
		}
		
	}
	
	//원자를 충돌시킴
	private static List<Pair> bomb(List<Pair> temps) {
		for (int i = 0; i < temps.size()-1; i++) {
			
			int sum = temps.get(i).energy;
			int start = i;
			//같은 좌표  찾기
			while(i+1 < temps.size() - 1 && temps.get(i).x == temps.get(i+1).x && temps.get(i).y == temps.get(i+1).y) {
				sum += temps.get(i+1).energy;
				result = sum;
				i++;
			}                                       
			
			temps.get(start).energy = sum;
			
			for (int j = start; j <= i; j++) {
				temps.remove(j);
				i--;
				j--;
			}

		}
		
		return temps;
	}
	
}
