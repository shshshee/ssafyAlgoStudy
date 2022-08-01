import java.io.IOException;
import java.util.Scanner;

public class Main {
	static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	
	public static int getResult(int num1, int num2, int operator) {
		if(operator == 0) {
			return num1 + num2;
		} else if(operator == 1) {
			return num1 - num2;
		} else if(operator == 2) {
			return num1*num2;
		} else { // operator == 3
			if(num1 < 0) {
				int temp = num1 * -1;
				return (-1)*(temp/num2);
			} else {
				return num1/num2;
			}
		}
	}
	
	// 모든 경우의 수를 구한다. 현재 피연산자 arr[idx] 와 arr[idx+1]을 계산하여 result에 더한뒤 다음으로 넘어간다.
	public static void solve(int[] arr, int[] operator, int idx, int ans) { 
		if(idx == n) { // 마지막까지 연산 완료
			max = Math.max(ans, max);
			min = Math.min(ans, min);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(operator[i] > 0) {
				operator[i]--; // i번째 연산자를 사용
				solve(arr, operator, idx+1,  getResult(ans, arr[idx], i));
				operator[i]++;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[] operator = new int[4];
		for(int i = 0; i < 4; i++) {
			operator[i] = sc.nextInt();;
		}
		
		solve(arr, operator, 1, arr[0]);
		System.out.println(max);
		System.out.println(min);
	}
}
