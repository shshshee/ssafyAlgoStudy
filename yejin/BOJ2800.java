import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static String input;
	static LinkedList<Pair> list;
	static LinkedList<String> result;
	static boolean[] deleted;

	// i번째 괄호를 삭제한 뒤에 나온 결과를 저장
	public static void solve(int i) {
		deleted[list.get(i).left] = true;
		deleted[list.get(i).right] = true;

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < input.length(); j++) {
			if (!deleted[j]) {
				sb.append(input.charAt(j));
			}
		}

		result.add(sb.toString());

		for (int j = i + 1; j < list.size(); j++) {
			solve(j); // 그 다음 순서로 j 번째 페어를 삭제하는 경우의 수
		}

		deleted[list.get(i).left] = false;
		deleted[list.get(i).right] = false;
	}

	public static void getResult() {
		for (int i = 0; i < list.size(); i++) {
			solve(i); // 첫번째로 i번째 페어를 삭제하는 경우의 수
		}
	}

	public static void getList(String input) {

		Stack<Integer> stack = new Stack<Integer>();
		list = new LinkedList<Pair>();

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '(') {
				stack.add(i);
			} else if (ch == ')') {
				list.add(new Pair(stack.pop(), i));
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();
		result = new LinkedList<String>();
		getList(input);

		deleted = new boolean[input.length()];
		getResult();

		Collections.sort(result);

		String old = "";
		for (String s : result) {
			if (old.contains(s)) { // 중복 제거
				continue;
			}
			System.out.println(s);
			old = s;
		}

	}
}

class Pair {
	int left, right;

	public Pair(int left, int right) {
		this.left = left;
		this.right = right;
	}
}
