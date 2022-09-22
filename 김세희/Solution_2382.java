package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2382 {
    static int N,M,K;
    static int[][] map;
    static ArrayList<microbe> mList;
    static PriorityQueue<microbe> pq;
    static int[][] dir = {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; // 방향은 상하좌우 순
    static class microbe{
        int x;
        int y;
        int num;
        int d;
        microbe(int x,int y,int num,int d){
            this.x = x;
            this.y = y;
            this.num = num;
            this.d = d;
        }
    }
    static int total;
    //미생물 격리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 셀의 갯수
            M = Integer.parseInt(st.nextToken()); // 격리 시간
            K = Integer.parseInt(st.nextToken()); // 미생물 군집의 갯수

            map = new int[N][N];
            mList = new ArrayList<>();
//            mList.add(new microbe(0,0,0,0));

            // 미생물의 수가 많은 순으로 정렬
            pq = new PriorityQueue<>((o1,o2)->(o2.num - o1.num));

            for(int i=1;i<K+1;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                mList.add(new microbe(x,y,num,d));
                map[x][y] = i; // map 배열에는 각 군집의 인덱스를 저장한다
            }

            total = 0;
            for(int i=0;i<M;i++){
                // m시간동안 이동
                move();
            }

            for(int i=0;i<mList.size();i++){
                total += mList.get(i).num;
            }

            System.out.println("#"+t+" "+total);

        }

    }

    static void move() {
        for (int i = 0; i < mList.size(); i++) {
            // 군집이 이동한 후의 위치를 큐에 저장한다.
            microbe cur = mList.get(i);

            map[cur.x][cur.y] = 0;

            int nx = cur.x + dir[cur.d][0];
            int ny = cur.y + dir[cur.d][1];
            int num = cur.num;
            int d = cur.d;

            pq.add(new microbe(nx,ny,num,d));

        }

        mList.clear();

        while(!pq.isEmpty()){
            microbe next = pq.poll();
            // 1. 범위 벗어났을경우 미생물이 절반으로 죽고 반대방향으로 전환
            if(next.x == 0 || next.y == 0 || next.x == N-1 || next.y == N-1 ){
                next.num /= 2;
                if(next.num==0) continue;
                // 방향전환
                if(next.d == 2 || next.d == 4){
                    next.d -= 1;
                }else{
                    next.d += 1;
                }

                // 다시 이동하기 위해 리스트에 넣어주기
                mList.add(next);

            }else{
                if(map[next.x][next.y]==0){
                    // 다음 이동을 위해 리스트에 다시 객체를 넣어주고, map배열 값을 해당 객체의 인덱스값으로 갱신.
                    // 방향은 그대로 사용한다. ( pq를 사용하여 가장 미생물 수가 많은 것부터 나왔기 때문 )
                    mList.add(next);
                    map[next.x][next.y] = mList.size();
                }else{
                    // 2. 충돌하였을 경우 (map에 이미 값이 있는 경우) -> 미생물 수 합쳐주기
                    mList.get(map[next.x][next.y]-1).num += next.num;
                }

            }
        }


    }
}
