import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj17413_단어뒤집기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();

        // 조건
        // 태그는 단어가 아님/Users/sehee/Desktop/boj17413_단어뒤집기2.java
        // 두 단어는 공백 하나로 이루어져 있다.

        // 1.공백으로 단어 분리
        String[] arr = str.split(" ");
        Stack<Character> stack = new Stack<>();

        boolean isTag = false; 
        for(int i=0;i<arr.length;i++){

            for(int j=0;j<arr[i].length();j++){
                if(arr[i].charAt(j)=='<'){ // 태그의 시작일 경우
                    isTag = true;
                    while(!stack.isEmpty()){ // 태그 시작 이전 스택에 쌓아놓은 단어 차례로 출력 
                        sb.append(stack.pop());
                    }
                }
                if(isTag) {
                    sb.append(arr[i].charAt(j)); // 태그 사이에 있는 단어는 스택으로 넣지않고 그대로 출력 
                    if ((arr[i].charAt(j) == '>')) {
                        isTag = false;
                    }
                }else{ // 태그가 아닌경우 스택에 넣가
                    stack.push(arr[i].charAt(j));
                }
            }
            
            // 공백으로 분류된 하나의 단어가 모두 스택에 쌓였을 경우 하나씩 pop
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }

            sb.append(' ');

        }

        System.out.println(sb);

    }
}
