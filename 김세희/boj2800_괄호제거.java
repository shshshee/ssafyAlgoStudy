import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class boj2800_괄호제거 {

    // 괄호 시작점, 끝점 위치 저장을 위한 클래스
    static class spot{
        int s;
        int e;

        spot(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
    static boolean[] visited;
    static char[] arr;
    static ArrayList<spot> brackets;
    static Set<String> resultSet;
    // 괄호 쌍을 제거해서 나올 수 있는 서로 다른 식을 사전순으로 출력하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        arr = str.toCharArray();

        Stack<Integer> stack = new Stack<>();
        brackets = new ArrayList<>(); // 각 괄호의 위치들을 저장하기 위한 리스트

        for(int i=0;i<arr.length;i++){
            if(arr[i]=='('){
                stack.push(i);
            }else if(arr[i]==')'){
                brackets.add(new spot(stack.pop(),i));
            }
        }

        visited = new boolean[brackets.size()];

        // 결과를 저장하기 위한 set
        // 나올수 있는 서로 다른 식을 저장해야 하기 때문, 중복 불가 -> set으로 받기
        resultSet = new HashSet<>();

        Combi(brackets.size(),0);

        resultSet.stream().sorted().forEach(System.out::println);

    }

    // 부분집합
    static void Combi(int n, int idx){
        if(idx == n) {
            boolean[] isDelete = new boolean[arr.length];
            int deleteCnt = 0;

            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    spot now = brackets.get(i);
                    isDelete[now.s] = true;
                    isDelete[now.e] = true;
                    deleteCnt++;
                }
            }

            if(deleteCnt==0) return; // 공집합인 경우, -> 지울 괄호가 없는 경우 그대로 리턴

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<arr.length;i++){
                if(!isDelete[i]) sb.append(arr[i]);
            }

            String result = sb.toString();
            resultSet.add(result);
            return;
        }

        visited[idx] = true;
        Combi(n,idx+1);

        visited[idx] = false;
        Combi(n, idx+1);
    }

}
