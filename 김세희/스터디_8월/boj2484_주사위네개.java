import java.util.*;
import java.io.*;
public class boj2484_주사위네개 {
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int result = 0;
		for(int i=0;i<N;i++) {
			arr = new int[7];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[num]++;
			}
			
			
			result = Math.max(getMoney(),result);
			
		}
		
		System.out.println(result);
		
		
	}
	
	static int getMoney() {
		
		ArrayList<Integer> two = new ArrayList<>();
		int max = 0;
		
		for(int j=1;j<7;j++) {
			if(arr[j]==4) {
				return 50000+j*5000;
			}else if(arr[j]==3) {
				return 10000+j*1000;
			}else if(arr[j]==2) {
				two.add(j);
			}else if(arr[j]==1) {
				max = Math.max(j, max);
			}
		}
		
		// 같은 눈이 두 쌍 나오는 경우
		if(two.size()==2) {
			return 2000 + two.get(0)*500 + two.get(1)*500;
		}else if(two.size()==1) {
			return 1000 + two.get(0)*100;
		}else {
			return max*100;
		}
	}

}
