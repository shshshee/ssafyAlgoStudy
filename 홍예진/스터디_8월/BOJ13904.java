import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13904 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Task> pq = new PriorityQueue<>(new Comparator<Task>() {
			public int compare(Task t1, Task t2) {
				return t2.w == t1.w ? t1.d - t2.d : t2.w - t1.w;
			}
		}); // 점수 높은 순. 점수가 같다면 날짜가 가까운 순
		int[] day = new int[1001];

		// 오늘 날짜가 있다면 오늘날짜 중 점수가 가장 높은것을 더한다.
		// 오늘 날짜가 없다면 남은 것중 점수가 가장 높은 것을 더한다.
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Task t = new Task(d, w);
			pq.add(t);
		}

		int ans = 0;
		
		while(!pq.isEmpty()) {
			Task t = pq.poll();
			int idx = t.d;
			while(idx > 0) {
				if(day[idx] == 0) {
					day[idx] = t.w;
					ans += t.w;
					break;
				}
				idx--;
			}
		}
		
		
		
		System.out.println(ans);
	}
}

class Task {
	int d, w;

	public Task(int d, int w) {
		this.d = d;
		this.w = w;
	}

}
