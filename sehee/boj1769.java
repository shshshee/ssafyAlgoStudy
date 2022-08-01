import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1769 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // y가 한자리수가 될때까지 반복하기
        recursion(str,0);
	}
	
	static void recursion(String str, int cnt) {
        if(str.length()==1){ // 한자리수일때
            int num = Integer.parseInt(str);
            System.out.println(cnt);
            if(num%3==0){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
            return;
        }

		int y = 0;
        for(int i=0;i<str.length();i++){
            y += str.charAt(i)-'0';
        }

        recursion(String.valueOf(y),cnt+1);
	}

}
