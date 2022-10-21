package boj.p14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * boj 14501 퇴사
 * */
public class Main_14501 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] times = new int[N+1];
		int[] prices = new int[N+1];
		int[] dp = new int[N+1];
		
 		for (int i = 1; i < N+1; i++) {
 			st = new StringTokenizer(br.readLine());
 			times[i] = Integer.parseInt(st.nextToken());
 			prices[i] = Integer.parseInt(st.nextToken());
 			dp[i] = prices[i];//기존 값 미리 세팅
		}
 		 		
 		
 		for (int i = 1; i < N+1; i++) {
 			if(i+times[i] >= N+2) {
 				dp[i] = 0;
 				continue;
 			}
			for (int j = 1; j < i; j++) {
				if(i-j >= times[j]) {
					dp[i] = Math.max(dp[i], dp[j] + prices[i]);
				}
			}
		}
		
 		int result = Integer.MIN_VALUE;
 		for (int i = 1; i < N+1; i++) {
			result = Math.max(result, dp[i]);
		}
 		
 		//System.out.println(Arrays.toString(dp));
 		System.out.println(result);
	}
}
