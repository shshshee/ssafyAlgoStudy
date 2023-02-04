import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj11725_트리의부모찾기 {
    static ArrayList<Integer>[] tree;
    static int[] result;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N+1];
        result = new int[N+1];
        for(int i=0;i<N+1;i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        visited = new boolean[N+1];

        getParent(1);

        for(int i=2;i<N+1;i++){
            System.out.println(result[i]);
        }

    }

    static void getParent(int start){

        for(int next : tree[start]){
            if(!visited[next]){
                visited[next] = true;
                result[next] = start;
                getParent(next);
                visited[next] = false;
            }
        }

    }
}
