package baekJoon;

import java.util.Scanner;
public class Main_no1769 {
	static int cnt = 0; // 변환 횟수
	static void sumDigits(int x) {
		cnt++;
		String strX = Integer.toString(x);
		int sum = 0;
		// 1. 자릿수의 합 구하기
		for(int i = 0; i < strX.length(); i++) {
			sum += strX.charAt(i) - '0';
		}
		// 2. 한 자리수인가?
		if(sum / 10 == 0) {
			// 4. 몇 번 변환 과정을 거쳤는지
			System.out.println(cnt);
			// 5. 3의 배수이면 YES, 아니면 NO
			if(sum % 3 == 0) System.out.println("YES");
			else System.out.println("NO");
		}
		// 3. 아니라면 1~2 반복
		else {
			sumDigits(sum);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		
		sumDigits(x);
		
	}

}
