import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		LinkedList<Lesson> q = new LinkedList<Lesson>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			q.add(new Lesson(s, e));
		}

		int count = 1;
		int qSize = q.size();
		while (!q.isEmpty()) {
			Collections.sort(q);
			int last = 0;
			while (qSize-- > 0) {
				Lesson l = q.poll();
				//System.out.println(l.start +" "+l.end);
				if (last <= l.start)
					last = l.end;
				else
					count++;
			}
			qSize = q.size();
		}

		System.out.println(count);

	}
}

class Lesson implements Comparable<Lesson> {
	int start, end;

	public Lesson(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Lesson l) {
		return this.end == l.end ? l.start - this.start : this.end - l.end;
	}
}
