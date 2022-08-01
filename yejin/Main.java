import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int count = 0;
	public static int solve(String input) {
//		System.out.print(input +" => ");
		
		int x = Integer.parseInt(input);
		if(x < 10) {
			return x;
		}
		
		count++;
		x = 0;
		char[] chars = input.toCharArray();
		for(char c : chars) {
			x += Character.getNumericValue(c);
		}
		
		return solve(String.valueOf(x));
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		int x = solve(input);
		System.out.println(count);
		System.out.println(x%3 == 0 ? "YES" : "NO");
	}
}
