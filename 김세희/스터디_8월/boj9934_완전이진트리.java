

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj9934_완전이진트리 {
    static ArrayList<ArrayList<Integer>> list;
    static int size;
    static int[] node;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        size = (int)Math.pow(2,N)-1;

        list = new ArrayList<>();
        node = new int[size];

        for(int i=0;i<size;i++){
            list.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<size;i++){
            node[i] = Integer.parseInt(st.nextToken());
        }

        solve(0,size,0);

        for(int i=0;i<N;i++){
            for(Integer n : list.get(i)){
                System.out.print(n+" ");
            }
            System.out.println();
        }

    }

    static void solve(int s, int e, int depth){
        if(s>=e) return;

        int mid = (s+e)/2;

        list.get(depth).add(node[mid]);

        solve(s,mid,depth+1); // 왼쪽자식
        solve(mid+1,e,depth+1); // 오른쪽자식


    }

}
