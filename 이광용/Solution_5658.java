package SWEA_AD;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5658 {
	private static Queue<Character> q;
	private static int N;
	private static int K;
	private static char last;
	private static HashSet<String> hs;
	private static ArrayList<Integer>  answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			//한 변의 숫자의 길이는 N / 4 => 총 회전 수를 의미
			//큐를 만들어서 한 회전마다 그 길이마다 값의 크기 확인하고 
			//다시 넣을 땐, 맨 마지막 원소부터 넣는다.
			q = new LinkedList<>();
			char arr[]= br.readLine().toCharArray();
			last =' ';//마지막 원소를 기억 할 변수
			for(int i = 0; i < N; i++) {
				q.offer(arr[i]);
				//마지막 원소 기억하기
				if(i == N-1) last = arr[i]; 
			}
			
			//십진수 변환 메소드
//			int temNum= convertNum(new char[]{'1', 'B', '3'});
			//중복 방지 hashset
			hs = new HashSet<>();
			answer = new ArrayList<>();
			Rotate();
			//내림차순
			Collections.sort(answer, Collections.reverseOrder());
			System.out.println("#" + tc + " "+ answer.get(K-1));
			
		}//end of test_case
	}

	private static void Rotate() {
		
		for(int i = 0; i < N / 4; i++) { //회전 수
//			Queue<Character> nextQ = new LinkedList<>();  
			q.offer(last); //제일 먼저 미리 저장해둔 마지막 원소 값을 넣는다.
			for(int j = 0; j < 4; j++) { //4변에 각각 숫자가 만들어짐
				String tmp = "";//중복 확인 변수
				char[] convertArr = new char[N / 4];//십진수로 변경 할 배열
				for(int k = 0; k < N / 4; k++) { //한 변의 길이
					char c =q.poll();
					tmp += c;
					convertArr[k] = c;
					
					if(j == 3 && k == N/4 -2) { //마지막 4번째 변이면서 마지막 직전 원소라면
						last = c;//다음 회전에 쓰일 last갱신
					}
					if(j == 3 && k == N/4 -1) { //마지막 4번째 변이면서 마지막 원소라면
						continue; //다음 회전에 쓰일 q의 첫 원소로 이미 들어가 있으므로 넘긴다.
					}
					q.offer(c); //다음 회전에 쓰일 Queue에 삽입
				}//end of 한 변
				if(hs.contains(tmp)) continue;
				else {
					hs.add(tmp);
					answer.add(convertNum(convertArr));
				}
			}//end of 4변
		}//end of 한번의 회전
	}//end of Rotate()

	private static int convertNum(char[] convertArr) {
		int tenNum = 0; //십진수
		int posSize = 1; //16진수의 자리수에 따른 크기 
		for(int i = convertArr.length-1; i >= 0; i--) {
			int number = 0;
			if((convertArr[i] - '0') > 9) { //숫자가 아닌 알파벳
				number = convertArr[i] - 'A' + 10;
				
			}else {
				number = convertArr[i] - '0';
			}
			tenNum += posSize * number;
			posSize *= 16;
		}
		return tenNum;
	}//end of convertNum()
	
}

