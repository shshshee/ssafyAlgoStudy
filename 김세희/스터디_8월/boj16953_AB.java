package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj16953_AB {
    static int result = -1;
    static long A,B;
    static class num{
        long n;
        int cnt;
        num(long n,int cnt){
            this.n = n;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // A를 B로 바꾸는데 연산의 최솟값구하기
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        bfs();

        if(result!=-1){
            System.out.println(result);
        }else{
            System.out.println(result);
        }

    }

    static void bfs(){
        Queue<num> queue = new LinkedList<>();
        queue.add(new num(A,0));

        while(!queue.isEmpty()){
            num now = queue.poll();

            if(now.n == B){
                result = now.cnt+1;
                return;
            }

            long next1 = now.n*10+1;
            long next2 = now.n*2;

            if(next1<=B) queue.add(new num(next1,now.cnt+1));
            if(next2<=B) queue.add(new num(next2,now.cnt+1));

        }

    }

}
