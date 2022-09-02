package N10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13904_과제 {
	static class Assignment implements Comparable<Assignment> {
		int time;
		int score;

		public Assignment(int time, int score) {
			this.time = time;
			this.score = score;
		}

		// 시간순 정렬
		@Override
		public int compareTo(Assignment o) {
			// 점수 높은 걸로
			if (this.time == o.time) {
				return o.score - this.score;
			}
			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws IOException {
		int res = 0;
		int dDay = 0;
		PriorityQueue<Integer> sequence = new PriorityQueue<>();

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Assignment[] assign = new Assignment[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			assign[i] = new Assignment(t, s);
		}

		// 정렬
		Arrays.sort(assign);

		int max = Integer.MIN_VALUE;

		for (int k = 0; k < assign.length; k++) {
			dDay = 0;
			for (int i = k; i < assign.length; i++) {
				dDay++;
				if (assign[i].time < dDay) {
					continue;
				} else {
					res += assign[i].score;
				}
			}
			Math.max(max, res);
		}

//		// 우선순위 큐에 점수 순으로 넣기
//		sequence.offer(assign[0].score);
//
//		for (int i = 0; i < N; i++) {
//			dDay++;
//			if (assign[i].time < dDay) {
//				continue;
//			}
//			if (assign[i].score >= sequence.peek()) {
//				res+= sequence.peek();
//				System.out.println(sequence.peek());
//				sequence.poll();
//			}
//
//			sequence.offer(assign[i].score);
//		}

		System.out.println(max);
	}

}
//1 20
//2 50
//3 30
//4 60
//4 40
//4 10

//4 60
//2 50
//4 40
//3 30
//1 20
//4 10