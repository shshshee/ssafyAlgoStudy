import java.util.Scanner;

public class Main {
	static int K = 0;
	static StringBuilder sb = new StringBuilder();

	// now위의 count개수의 원판을 target으로 옮긴다.
	public static void hanoi(int count, int now, int target) {
		if (count == 0)
			return;

		int extra = 6 - now - target; // 남은 원판
		hanoi(count - 1, now, extra); // 가장 큰 판 하나를 남기고 남은 원판에 전부 옮긴다
		K++;
		sb.append(now + " " + target+"\n"); // 가장 큰 판을 타겟 판에 옮긴다.
		hanoi(count - 1, extra, target); // extra에 옮겨놨던 나머지 판을 다시 target에 전부 옮긴다.
	}

	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt();

		hanoi(N, 1, 3);

		System.out.println(K);
		System.out.println(sb);
	}
}
