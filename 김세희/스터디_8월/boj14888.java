import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj14888 {
    // 만들수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램
    static int[] numArr;
    static int[] operator;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 숫자
        numArr = new int[N];
        operator = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            int n  = Integer.parseInt(st.nextToken());
            operator[i] = n;
        }


        getResult(1,N,numArr[0]);

        System.out.println(max);
        System.out.println(min);

    }

    static void getResult(int cnt, int N, int num){

        if(cnt==N){
            max = Math.max(max,num);
            min = Math.min(min,num);
            return;
        }

        for(int i=0;i<4;i++){
            if(operator[i]<=0) continue;

            operator[i]--;

            if(i==0){ // +
                getResult(cnt+1,N,num+numArr[cnt]);
            }else if(i==1){ // -
                getResult(cnt+1,N,num-numArr[cnt]);
            }else if(i==2){ // *
                getResult(cnt+1,N,num*numArr[cnt]);
            }else{ // /
                getResult(cnt+1,N,num/numArr[cnt]);
            }

            operator[i]++;

        }
    }
}
