import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Solution {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static class core{
        int x;
        int y;
        core(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<core> coreList;
    static int[] tmp; // 중복순열 저장하기 위한
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
    static int minLength = Integer.MAX_VALUE;
    static int coreCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
 
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            coreList = new ArrayList<>();
            //infoList = new ArrayList<>();
 
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 이미 전원에 연결되어있는 코어 제외하고 연결해야할 나머지 코어를 리스트에 넣어준다.
                    if(i>0 && j>0 && i<N-1 && j<N-1 && map[i][j]==1){
                        coreList.add(new core(i,j));
                    }
                }
            }
 
            tmp = new int[coreList.size()]; // 각 코어별로 전선 설치할 방향정보 저장하기 위한 배열 생성
            solve(0);
            //Collections.sort(infoList);
            //int result = infoList.get(0).size;
            sb.append('#').append(t+" ").append(minLength).append('\n');
        }
 
        System.out.println(sb);
 
    }
 
    static void solve(int depth){
       if(coreList.size()==depth){
           visited = new boolean[N][N];
           int cnt = 0;
           int length = 0;
           for(int i=0;i<tmp.length;i++){
               int len = connect(i);
               if(len>0) {
                   cnt++;
                   length += len;
               }
           }
 
           if(cnt>coreCount){
               minLength = length;
               coreCount = cnt;
           }else if(cnt==coreCount){
               minLength = Math.min(minLength,length);
           }
           return;
       }
 
       // 방향정보 -> 중복순열
        for(int i=0;i<4;i++){
            tmp[depth] = i;
            solve(depth+1);
        }
    }
 
    // 전선 연결하기
    static int connect(int idx){
 
        int nx = coreList.get(idx).x;
        int ny = coreList.get(idx).y;
        int d = tmp[idx]; // 방향정보
 
        int length = 0;
        for(int k=0;k<N;k++){
            nx += dir[d][0];
            ny += dir[d][1];
 
            // 전선을 설치할 수 없는 경우) 1. 이미 전선이 지나고 있거나 2. 코어가 위치해있는 경우
            if(nx >= N || ny >= N || nx < 0 || ny < 0) return length;
            if(map[nx][ny]>0 || visited[nx][ny]) return 0;
 
            visited[nx][ny]=true;
            length++;
        }
 
        return length;
    }
 
}