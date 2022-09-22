package swea.p2382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_2382 {
	
	private static int[][] dir = {{},{-1,0}, {1,0},{0,-1},{0,1}};//상하좌우
	private static int result;
	private static LinkedList<Pair> locations;
	
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		int value;//미생물 수
		int move_dir;//이동방향, (상: 1, 하: 2, 좌: 3, 우: 4)
		
		public Pair(int x, int y, int value, int move_dir) {
			this.x = x;
			this.y = y;
			this.value = value;
			this.move_dir = move_dir;
		}

		@Override
		public int compareTo(Pair o) {
			if(this.x==o.x && this.y == o.y) {
				return -(this.value - o.value);
			}
			return this.x - o.x;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());//셀의 개수
			int M = Integer.parseInt(st.nextToken());//격리 시간
			int K = Integer.parseInt(st.nextToken());//미생물 군집의 개수
			
			locations = new LinkedList<Pair>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				//locations.add(new Pair(x, y, size, direction));
				locations.add(new Pair(x, y, size, direction));
			}
			solve(M, N);
			
			System.out.println("#" + test_case + " " + result);
			//sb.append("#").append(test_case).append(" ");
			//sb.append(result).append("\n");
		}
		//System.out.println(sb.toString());
	}
	
	
	//로직 : 모든 미생물 이동시킨 후에 합쳐지는 미생물 처리하기
	private static void solve(int M, int N) {
		//Queue<Pair> temp = new LinkedList<Pair>();
		while(M-- > 0) {
			LinkedList<Pair> temp = new LinkedList<Pair>();
			for (int i = 0; i < locations.size(); i++) {
				
				Pair now = locations.get(i);
				int nowX = now.x;
				int nowY = now.y;
				int nowValue = now.value;
				int nowDir = now.move_dir;
				
				int nextX = nowX + dir[nowDir][0];
				int nextY = nowY + dir[nowDir][1];
				int nextDir = 0;
				
				if(nextX == 0 || nextY == 0 || nextX == N-1 || nextY == N-1) {//약품이 칠해진 구역(가장 자리의 빨간 셀)
					if(nowDir == 1) nextDir = 2;
					else if(nowDir == 2) nextDir = 1;
					else if(nowDir == 3) nextDir = 4;
					else nextDir = 3;
					
					if(nowValue/2 <= 1) {//미생물 죽음 
						locations.remove(i);
						i--;
						continue;
					}
					temp.add(new Pair(nextX, nextY, nowValue/2, nextDir));
					continue;
				}
				temp.add(new Pair(nextX, nextY, nowValue, nowDir));
			} 
		
			Collections.sort(temp);
			
			locations.clear();
			//합쳐지는 미생물 처리하기 
			locations = combine(temp);
		}
		result = count(locations);
	}
	
	//합쳐지는 미생물 처리하기
	//합쳐지는 미생물의 개수가 3개 이상인 경우 ??->이 중에서 가장 세포가 많은 군집의 방향으로 
	private static LinkedList<Pair> combine(LinkedList<Pair> temp) {
		
		for (int i = 0; i < temp.size()-1; i++) {
			
			int sum = temp.get(i).value;
			int start = i;
			//같은 좌표  미생물 찾기
			while(i+1 < temp.size() - 1 && temp.get(i).x == temp.get(i+1).x && temp.get(i).y == temp.get(i+1).y) {
				sum += temp.get(i+1).value;
				i++;
			}                                       
			
			//합치기
			//같은 좌표에 대해 우선 순위에 따라 맨 앞의 요소가 큰 값			
 			temp.get(start).value = sum;
			
			for (int j = start+1; j <= i; j++) {
				temp.remove(j);
				i--;
				j--;
			}

		}
			
		return temp;
	}
	
	private static int count(LinkedList<Pair> temp) {
		int sum = 0;
		for (int i = 0; i < temp.size(); i++) {
			sum += temp.get(i).value;
		}
		
		return sum;
	}
	
}
