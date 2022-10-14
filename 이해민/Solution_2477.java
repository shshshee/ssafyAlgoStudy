package swea.p2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2477 {
	static int A, K, M, N, B;
	private static int[] a_time;
	private static int[] b_time;
	//private static int[] tk;
	static PriorityQueue<Customer> ready;

	static class Customer {
		int num;
		int time;
		int a;//접수 창구 번호
		int b;//정비 창구 번호

		public Customer(int num, int time, int a, int b) {
			super();
			this.num = num;
			this.time = time;
			this.a = a;
			this.b = b;
		}

		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());// 접수 창구의 개수
			M = Integer.parseInt(st.nextToken());// 정비 창구의 개수
			K = Integer.parseInt(st.nextToken());// 차량 정비소를 방문한 고객의 수
			A = Integer.parseInt(st.nextToken());// 접수 창구번호
			B = Integer.parseInt(st.nextToken());// 접수 창구번호

			a_time = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				a_time[i] = Integer.parseInt(st.nextToken());
			}

			b_time = new int[M];
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				b_time[j] = Integer.parseInt(st.nextToken());
			}

			ready = new PriorityQueue<Customer>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					return o1.time - o2.time;
				}
			});
			
			//tk = new int[K];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				//tk[k] = Integer.parseInt(st.nextToken());
				ready.add(new Customer(k, Integer.parseInt(st.nextToken()), -1, -1));
			}
			

			sb.append(String.format("#%d %d\n", test_case, 0));
		}
		System.out.println(sb);
	}

	/*
	 * 1. 접수 창구->정비 창구 큐 이동
	 * 2. 접수 창구 : 고객 번호 낮은 순서(우선순위 큐), 접수 창구 비어있으면 창구번호 적은 곳으로 이동
	 * 3. 정비 창구 : 창구번호가 작은 고객이 우선(우선순위 큐), 정비 창구 비어있으면 창구번호 적은 곳으로 이동
	 * 
	 * 지갑을 분실한 고객과 같은 접수 창구와 같은 정비 창구를 이용한 고객의 고객번호들을 찾아 그 합을 출력
	 * */
	static void solve() {
		
		PriorityQueue<Customer> reception = new PriorityQueue<Customer>(new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.time == o2.time ? o1.num - o2.num : o1.time - o2.time;
			}
		});
		
		PriorityQueue<Customer> repair = new PriorityQueue<Customer>(new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.time == o2.time ? o1.a - o2.a : o1.time - o2.time;
			}
		});

		
		boolean[] visitedReception = new boolean[N];
		boolean[] visitedRepair = new boolean[M];
		
		int time = 0;//현 시간
		
		while(true) {
			
			//접수 창구 배치
			for (int i = 0; i < N; i++) {
				Customer customer = ready.poll();
				if(!visitedReception[i] && customer.time == time) {
					visitedReception[i] = true;
					reception.add(new Customer(customer.num, customer.time, i, -1));
					time += customer.time;
				}
			}
			
			//접수 창구 배치			
			for (int j = 0; j < M; j++) {
				Customer customer = repair.poll();
				if(!visitedRepair[j]) {
					visitedReception[j] = true;
					repair.add(new Customer(customer.time, customer.time, customer.a, j));
				}
			}
			
			if(reception.isEmpty() && repair.isEmpty()) break;
		}
		

	}

}
