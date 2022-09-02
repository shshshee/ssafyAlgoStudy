package N9000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 중위순회(left, root, right) -> 레벨 순회 */

public class Main_9934_완전이진트리 {
	static int K; // 트리의 레벨
	static String[] city;
	static StringBuffer[] res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());
		city = new String[(int) Math.pow(2, K) - 1];
		for (int i = 0; i < K; i++) {
			res[i] = new StringBuffer();
		}

		city = br.readLine().split(" ");

		LevelOrder(0, city.length - 1, 0);
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
	}

	static void LevelOrder(int start, int end, int level) {
		if (level == K)
			return;

		int center = (end + start) / 2;
		res[level].append(city[center] + " ");
		LevelOrder(start, center - 1, level + 1); // left
		LevelOrder(center + 1, end, level + 1); // right
	}

}
