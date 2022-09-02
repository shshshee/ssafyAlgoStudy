package N1000;

import java.util.Scanner;

public class Main_1062_가르침 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 배운 단어 개수
		int K = sc.nextInt(); // 배울 수 있는 알파벳 개수

		// 배울 단어들 입력
		String[] words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = sc.next();
		}
		int res = 0;
		int alphabetCnt = 0; // 기존에 배운 알파벳 개수

		int[] alphabet = new int[26]; // 배운 알파벳 체크

		// 남극 필수 알파벳 따로 체크
		alphabet['a' - 'a']++;
		alphabet['n' - 'a']++;
		alphabet['t' - 'a']++;
		alphabet['i' - 'a']++;
		alphabet['c' - 'a']++;

		for (int i = 0; i < words.length; i++) {
			int newCnt = 0; // 새로 배운 알파벳

			// 해당 단어의 알파벳들 중에 anta 뒤부터 tica 앞까지만 고려
			for (int j = 4; j < words[i].length() - 4; j++) {
				// 새로 배운 것만 카운트
				if (alphabet[words[i].charAt(j) - 'a'] == 0)
					newCnt++;
				alphabet[words[i].charAt(j) - 'a']++;
			}
//			System.out.println(i + "번째 new : " + newCnt);

			// 뇌용량 초과라면 리셋
			if (alphabetCnt + newCnt > K - 5) {
				for (int j = 4; j < words[i].length() - 4; j++) {
					alphabet[words[i].charAt(j) - 'a']--;
					newCnt = 0;
				}
			}
			// 기존 배운거 + 새로 배운거가 뇌용량 한도내라면
			else {
				res++;
				// 지금까지 배운 알파벳 개수 기록
				alphabetCnt += newCnt;
			}
//			System.out.println(i + "번째 r : " + res);

		}

		System.out.println(res);
	}

}
