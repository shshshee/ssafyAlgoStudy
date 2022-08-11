import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1062_가르침 {
    static int N,K;
    static String[] word;
    static boolean[] check;
    static int max = Integer.MIN_VALUE;
    // 가르쳤을 떄 읽을 수 있는 단어 수의 최댓값 ..
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 가르쳐야할 단어 수

        word = new String[N];
        check = new boolean[26];

        for(int i=0;i<N;i++){
            word[i] = br.readLine(); // 단어 목록 저장 
        }

        if(K<5){ // 5미만인 경우 무조건 0이다
            System.out.println(0);
            return;
        }

        // a,n,t,i,c - 고정
        // a,n,t,i,c 를 제외하고 추가적으로 단어를 학습해야한다.
        check['a'-'a'] = true;
        check['n'-'a'] = true;
        check['t'-'a'] = true;
        check['i'-'a'] = true;
        check['c'-'a'] = true;

        K = K-5; // 추가 단어수 갱신

        dfs(0,0);
        System.out.println(max);

    }

    static void dfs(int start,int depth){
        if(K==depth){
            max = Math.max(max,getWordCount());
            return;
        }

        for(int i=start;i<26;i++){
            if(!check[i]){
                check[i] = true;
                dfs(i,depth+1);
                check[i] = false;
            }
        }

    }

    // 몇개를 읽을 수 있는지 카운트
    static int getWordCount(){
        int cnt = 0;
        boolean isRead;
        for(int i=0;i<N;i++){

            String str = word[i];
            isRead = true; // 초기값 true

            for(int n=0;n<str.length();n++){
                if(!check[str.charAt(n)-'a']){
                    isRead = false; // 못읽는 알파벳이 있는 경우
                    break;
                }
            }

            if(isRead) cnt++;

        }

        return cnt;
    }


}
