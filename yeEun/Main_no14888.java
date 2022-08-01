package yeEun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main_no14888 {
	static int n;
	static int[] arr = new int[n];
	static char[] opers = new char[n-1];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	static void cal(int n){
		int res = 0;
		for (int i = 0; i < n; i++) {
			switch(opers[i]) {
			case '+':
				break;
			case '-':
				break;
			case '*':
				break;
			case '/':
				break;
			default:
				break;
			}
		}
	}
	public static void main(String[] args) {
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		// 사칙연산 개수
		int[] arith = new int[4];
		for(int i = 0; i < arith .length; i++) {
			arith[i] = sc.nextInt();
		}
		
		// 사칙연산 하나하나
		for(int i = 0; i < opers.length; i++) {
			for(int j = 0; j < arith[i]; j++) {
				switch(i) {
				case 0:
					opers[i] = '+';
					break;
				case 1:
					opers[i] = '-';
					break;
				case 2:
					opers[i] = '*';
					break;
				case 3:
					opers[i] = '/';
					break;
				default:
					break;
				}
				
			}
		}

	}
}
