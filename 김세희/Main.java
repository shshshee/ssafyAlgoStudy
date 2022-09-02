import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class doc{
		int idx;
		int cost;
		doc(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++) {
			Queue<doc> q = new LinkedList<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());

			for(int i=0;i<n;i++) {
				int c = Integer.parseInt(st.nextToken()); // 중요도 
				
				q.add(new doc(i,c));
				pq.add(c);
			}
			
			int cnt = 1;
			while(true) {
				doc now = q.poll();
				
				if(now.idx == idx && now.cost == pq.peek()) {
					break;
				}
				
				if(now.cost == pq.peek()) {
					pq.poll();
					cnt++;
				}else {
					q.add(now);
				}
				
//				for(doc d : q){
//					System.out.print(d.cost+" ");
//				}
//				System.out.println();
//				
				
			}
			
			System.out.println(cnt);
			
			
		}
		
		
	}

}
