import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj9205_맥주마시면서걸어가기 {
    static class spot{
        int x;
        int y;
        spot(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<spot> list;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t=0;t<tc;t++){
            int n = Integer.parseInt(br.readLine());
            list = new ArrayList<>();

            graph = new ArrayList<>();
            for(int i=0;i<n+2;i++){
                graph.add(new ArrayList<>());
            }

            for(int i=0;i<n+2;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new spot(x,y));
            }

            for(int i=0;i<n+1;i++){
                for(int j=i+1;j<n+2;j++){
                    int dist = getDist(list.get(i),list.get(j));
                    if(dist<=1000){
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }

            if(bfs()){
                System.out.println("happy");
            }else System.out.println("sad");


        }

    }

    static boolean bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // 출발점, 상근이집
        boolean[] visited = new boolean[list.size()];

        while(!queue.isEmpty()){
            int cur = queue.poll();

            // 끝까지 잘 도착했다면
            if(cur == list.size()-1) return true;

            for(int next : graph.get(cur)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        // 도착하지 못할 경우
        return false;

    }

    static int getDist(spot s1, spot s2){
        return Math.abs(s1.x-s2.x) + Math.abs(s1.y-s2.y);
    }
}
