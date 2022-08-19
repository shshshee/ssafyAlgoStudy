import java.util.Scanner;

public class BOJ16953 {
	static int ans;

	public static void solve(long a, long b, int count) {
		if (a == b) {
			ans = Math.min(ans, count);
			return;
		}
		if (a > b)
			return;

		solve(a * 2, b, count + 1);
		solve(a * 10 + 1, b, count + 1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextInt();
		long B = sc.nextInt();
		ans = Integer.MAX_VALUE;
		solve(A, B, 1);
		
		System.out.println(ans == Integer.MAX_VALUE? -1 : ans);
	}
}
