package swea.p1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952 {
	private static int[] months;
	private static int[] price;
	private static int result;


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			price = new int[4];// 이용권 가격들

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < price.length; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			months = new int[13];// 12개월 이용 계획
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < months.length; i++) {
				months[i] = Integer.parseInt(st.nextToken());
			}

			result = price[3];// 1년 이용권으로 세팅

			solve(1, 0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	static void solve(int cnt, int cost) {
		if (cnt >= 13) {
			result = Math.min(result, cost);
			return;
		}

			
		int month = months[cnt];
		if(month == 0) {
			solve(cnt+1, cost);
		}else {
			// 1일 이용권
			solve(cnt+1, cost + price[0] * month);
			
			// 1달 이용권
			solve(cnt+1, cost + price[1]);
			
			// 3달 이용권
			solve(cnt+3, cost + price[2]);			
		}
			
		
	}

}
/*
1
10 40 100 300   
0 0 2 9 1 5 0 0 0 0 0 0
 * */
