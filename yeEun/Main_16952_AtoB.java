
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16952_AtoB {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int res = 1;
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int a = A;
		int b = B;

		while (b > A) {
			// 1-1. 2의 배수 o
			if (b % 2 == 0) {
				b /= 2;
			}
			// 1-2. 2의 배수 x
			else {
				// 2-1. 끝 자리가 1일 때
				if (b % 10 == 1) {
					b -= 1;
					b /= 10;
				}
			}
			res++;
		}
		if(b == A)
			System.out.println(res);
		else
			System.out.println(-1);

	}

}
