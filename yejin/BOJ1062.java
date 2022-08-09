import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, k;
	static String[] words;
	static int ans;
	static boolean[] use;

	public static boolean isReadable(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (!use[word.charAt(i) - 'a']) { // word에 사용된 알파벳이 사용되지 않는다면 읽을 수 없다.
				return false;
			}
		}
		return true;
	}

	// 현재까지 구한 알파벳 조합으로 읽을 수 있는 단어의 개수를 찾는다.
	public static void getAns() {
		int count = 0;
		for (String word : words) {
			if (isReadable(word)) {
				count++;
			}
		}
		ans = Math.max(ans, count);
	}

	// idx부터 시작하는 알파벳 리스트에서 count 개수의 알파벳을 고른다.
	public static void ps(int count, int idx) {
		
		if (count == 0) { // 알파벳을 다 골랐다.
			getAns();
			return;
		}
		
		if(idx >= 26) // 충분히 고르지 못하고 넘어감
			return;
		
		

		for (int i = idx; i < 26; i++) {
			if(use[i]) continue;
			use[i] = true;
			ps(count - 1, i + 1);
			use[i] = false;
		}

	}

	public static void solve() {
		use = new boolean[26]; // 알파벳을 사용했다는 것을 기록. 이 리스트에서 k개를 선택하는 조합을 저장하는 셈이다.
		char[] necessary = { 'a', 'n', 't', 'i', 'c' };
		for (char ne : necessary) { // 필수 알파벳 사용 표시
			use[ne - 'a'] = true;
		}

		ps(k - 5, 1); // 필수적인 알파벳 다섯개를 제외하고 나머지 글자 조합을 찾는다.
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		ans = Integer.MIN_VALUE;
		// 남극 언어는 anta - tica로 이루어져있으므로 최소 a, n, t, i, c를 배워야한다.
		if (k < 5) {
			System.out.println(0);
			return;
		}

		words = new String[n];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			words[i] = str.substring(4, str.length() - 4); // 불필요한 비교를 막기위해 접두사 anta와 접미사 tica 제거
		}

		solve();

		System.out.println(ans);
	}
}
