package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj11000_강의실배정 {
    static class time implements Comparable<time> {
        int s;
        int t;
        time(int s, int t){
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(time o) {
            if(this.s == o.s){
                return this.t - o.t;
            }
            return this.s - o.s;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        time[] lectures = new time[N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            lectures[i] = new time(s,t);
        }

        Arrays.sort(lectures);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures[0].t); // 끝나는시간 넣기

        for(int i=1;i<N;i++){
            if(!pq.isEmpty() && lectures[i].s >= pq.peek()){
                pq.poll();
            }
            pq.add(lectures[i].t);
        }

        System.out.println(pq.size());

    }
}
