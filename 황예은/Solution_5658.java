
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5658 {

	static int N, K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 0. 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 16진수 문자열 길이
			K = Integer.parseInt(st.nextToken()); // 몇 번째로 큰 숫자를 비번으로 쓸지
			String str = br.readLine(); // 16진수
			// --- 입력 끝

			// 1. str(문자열로 받은 16진수들)을 q에 거꾸로 넣기 (ex. 1B381F => F183B1)
			Queue<Character> q = new LinkedList<>();
			for (int i = str.length() - 1; i >= 0; i--) {
				q.offer(str.charAt(i));
			}

			// 2. 만들 수 있는 비번들 찾기
			ArrayList<Integer> pswdList = new ArrayList<>(); // 비번 후보들

			// 시계방향으로 돌리면 0 ~ (N/4 -1) 회전까지는 다르고 N/4회전부터 같은 내용(0: 무회전)
			for (int turn = 0; turn < N / 4; turn++) {
				// q를 N/4씩, 4묶음(보물상자의 변의 개수)으로 자르기
				for (int i = 0; i < 4; i++) {
					char[] tmpArr = new char[N / 4]; // 한 변의 16진수 (ex. F18/3B1)
					for (int j = 0; j < tmpArr.length; j++) {
						tmpArr[j] = q.poll();
//						System.out.print(tmpArr[j]); // [DEBUG]
					}
//					System.out.println(); // [DEBUG]

					// 16진수를 10진수로 만들기
					int pswd = 0;
					// (ex. F18 => 18F => 399, 3B1 => 1B3 => 435)
					for (int j = 0, index = 0; j < tmpArr.length; j++, index++) {
						if (tmpArr[j] >= '0' && tmpArr[j] <= '9') {
							pswd += (tmpArr[j] - '0') * Math.pow(16, index);
						} else {
							pswd += (tmpArr[j] - 'A' + 10) * Math.pow(16, index);
						}
					}

					// 10진수로 바꾼 비번을 pswdList에 넣기(리스트에 없는 번호라면)
					if (!pswdList.contains(pswd))
						pswdList.add(pswd);
				}

				// 시계방향으로 돌리기 = q의 맨 앞글자를 맨 뒤로 보내기
				for (int i = str.length() - 1; i >= 0; i--) {
					q.offer(str.charAt(i));
				}
				for (int i = 0; i <= turn; i++) {
					q.offer(q.poll());
				}
//				System.out.println(); // [DEBUG]
			}

			Collections.sort(pswdList, Collections.reverseOrder());

			// [DEBUG] pswd 리스트 내용물
//			System.out.println("pswd 내용물");
//			for (int i = 0; i < pswdList.size(); i++) {
//				int tmp = pswdList.get(i);
//				System.out.print(tmp + " ");
//			}

			System.out.println("#" + t + " " + pswdList.get(K - 1));
//			// k번째로 큰 숫자를 16진수로 만들기
//			String res = Integer.toHexString(pswdList.get(K-1));
//			// 정답 출력
//			System.out.println("#" + t + " "+res.toUpperCase());
		}
	}

}
