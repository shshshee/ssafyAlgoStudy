import java.util.Arrays;
import java.util.Scanner;

// 1 -> 1
// 2 -> 5
// 3 -> 9
// An = 1+4(n-1) = 4n - 3
public class BOJ10994 {
	static int N;
	static char arr[][];
	static char star = '*';

	public static void solve(int row, int col, int length) {

		if (length == 1) {
			arr[row][col] = star;
			return;
		}

		for (int j = col; j < col + length; j++) {
			arr[row][j] = star; // 위쪽 변
			arr[row + length - 1][j] = star; // 아랫쪽 변
		}

		for (int i = row; i < row + length; i++) {
			arr[i][col] = star; // 왼쪽 변
			arr[i][col + length - 1] = star; // 오른쪽 변
		}

		solve(row + 2, col + 2, length - 4);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new char[4 * N - 3][4 * N - 3];
		for(char[] a : arr)
			Arrays.fill(a, ' ');
		
		solve(0, 0, 4 * N - 3);

		print();

	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		for (char[] a : arr) {
			String line = String.copyValueOf(a);
			sb.append(line);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
