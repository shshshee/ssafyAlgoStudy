package ag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj18352_특정거리의도시찾기 {
    static class node{
        int n;
        int cost;
        node(int n,int cost){
            this.n = n;
            this.cost = cost;
        }
    }
    static ArrayList<node>[] path;
    public static void main(String[] args) throws IOException {
        // 최단거리가 정확히 k인 모든 도시들의 번호를 출력하는 프로그램을 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시의 갯수
        int M = Integer.parseInt(st.nextToken()); // 도로의 갯수
        int K = Integer.parseInt(st.nextToken()); // 거리정보
        int X = Integer.parseInt(st.nextToken()); // 출발 도시 정보

        int[] dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        path = new ArrayList[N+1];
        for(int i=0;i<N+1;i++){
            path[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            path[start].add(new node(end,1)); // 단방향
        }

        dist[X] = 0; // 출발도시거리 0

        PriorityQueue<node> pq = new PriorityQueue<>((o1,o2)->(o1.cost - o2.cost));
        pq.add(new node(X,0)); // 시작점부터 출발하기

        while(!pq.isEmpty()){
            node cur = pq.poll();
            if(cur.cost > dist[cur.n]) continue; // 이미 갱신된 경우

            for(node next : path[cur.n]){
                if(dist[next.n] > dist[cur.n] + next.cost){
                    dist[next.n] = dist[cur.n] + next.cost;
                    pq.add(new node(next.n, dist[next.n]));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        /// 결과 : 오름차순으로 출력
        for(int i=1;i<dist.length;i++){
            if(dist[i]==K) result.add(i);
        }

        if(result.size()==0){
            System.out.println(-1);
            return;
        }

        Collections.sort(result);
        for(int ans : result){
            System.out.println(ans);
        }


    }


}
