package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5648 {
    static int N;
    static int[][] map = new int[4001][4001];;
    static class atom{
        int x;
        int y;
        int d;
        int k;
        atom(int x, int y, int d, int k){
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }
    static int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}}; // 상 하 좌 우 순
    static Queue<atom> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            // bfs를 돌면서 지나간 자리는 값을 0으로 바꿔주기 때문에 배열을 매번 초기화를 해줄필요가 없음 !!!!!
            //map = new int[4001][4001]; // x,y의 최대값 1000 -> 1000*4 해줌
            queue = new LinkedList<>();

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken())+1000;
                int y = Integer.parseInt(st.nextToken())+1000;
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                queue.add(new atom(x*2,y*2,d,k));
                map[x*2][y*2] = k;

            }

            int result = bfs();
            System.out.println("#"+t+" "+result);

        }
    }

    static int bfs(){
        int size = 0;
        int total = 0; // 총 에너지
        while(!queue.isEmpty()){
            size = queue.size();
            for(int i=0;i<size;i++){
                atom cur = queue.poll();
                // 1. 밖으로 나가면 소멸
                // 2. 부딪히면 -> 에너지 방출
                int nx = cur.x + dir[cur.d][0];
                int ny = cur.y + dir[cur.d][1];

                map[cur.x][cur.y] -= cur.k; // 이동

                if(nx >= 0 && ny >= 0 && nx <= 4000 && ny <= 4000){
                    queue.add(new atom(nx,ny,cur.d,cur.k));
                    map[nx][ny]+=cur.k;
                }
            }

            size = queue.size();
            for(int i=0;i<size;i++){
                atom next = queue.poll();
                if(map[next.x][next.y]!=next.k){
                    total += map[next.x][next.y];
                    map[next.x][next.y] = 0;
                }else{
                    queue.add(next);
                }
            }

        }

        return total;

    }
}
