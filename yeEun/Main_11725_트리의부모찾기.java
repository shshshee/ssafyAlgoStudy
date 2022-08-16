package N10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 노드 개수

		ArrayList<Integer> tree = new ArrayList<Integer>();
		tree.add(1); // head
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());

			// tmp1이 트리에 있는지 확인
			for (int i = 0; i < tree.size(); i++) {
				// 트리에 있다면 부모 노드이므로, tmp2를 자식으로 넣어주기
				if (tree.get(i) == tmp1) {
					tree.add(i + 1, tmp2);
				} else {
					// tmp2가 트리에 있다면 tmp2의 자식으로 tmp1 넣기
					if (tree.get(i) == tmp2) {
						tree.add(i + 1, tmp1);
					}
				}
			}
		}
	}
}
