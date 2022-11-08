package boj.p1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1253 {
	private static int[] nums;
	private static int N;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
	 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		result = 0;
		
		Arrays.sort(nums);
		
		solve();
		
		System.out.println(result);
	}
	
	static void solve() {
		
		for (int i = 0; i < N; i++) {
			int start = 0, end = N-1;
			int sum = 0;
			
			while(start < end){
				if(start == i) start++;
				else if(end == i) end--;
				
				sum = nums[start] + nums[end];
				
				if(sum < nums[i]) {
					start++;
				}else if(sum > nums[i]) {
					end--;
				}else {
					result++;
					break;
				}
			}			
		}
	}
	
}
