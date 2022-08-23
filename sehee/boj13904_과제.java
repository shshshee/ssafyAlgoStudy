import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj13904_과제 {
    static class homework implements Comparable<homework>{
        int d;
        int w;
        homework(int d, int w){
            this.d = d;
            this.w = w;
        }


        @Override
        public int compareTo(homework o) {
            // TODO Auto-generated method stub
            if(o.d == this.d) {
                return o.w - this.w;
            }
            return o.d - this.d;
        }

        @Override
        public String toString() {
            return d+" "+w;
        }

    }
    static ArrayList<homework> list;
    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.add(new homework(d,w));
        }

        Collections.sort(list); // 정렬하기

        int today = list.get(0).d;
        int result = 0;
        PriorityQueue<homework> pq = new PriorityQueue<>((o1,o2)->(o2.w-o1.w));
        boolean[] visited = new boolean[list.size()]; // 큐에 중복으로 들어가는 것을 방지하기위한 visited 배열

        for(int i=today;i>=1;i--) {

            for(int j=0;j<list.size();j++) {
                if(list.get(j).d >= i && !visited[j]) {
                    pq.add(list.get(j));
                    visited[j] = true;
                }
            }

            if(!pq.isEmpty()) {
                homework hw = pq.poll();
                result += hw.w; // 점수 더해주기
            }

        }

        System.out.println(result);



    }

}
