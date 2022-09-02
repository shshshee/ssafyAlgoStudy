import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		String input = new Scanner(System.in).nextLine();
		Stack<Character> stack = new Stack<Character>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '<') {

				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}


				while (true) {
					sb.append(input.charAt(i++));

					if (input.charAt(i + 1) == '>') {
						sb.append(input.charAt(i));
						sb.append(input.charAt(++i));
						break;
					}
				}
			} else if (input.charAt(i) == ' ') {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(' ');
			} else {
				stack.add(input.charAt(i));
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
	}
}
