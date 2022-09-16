import java.io.*;
import java.util.*;

public class Solution_5658 {

    public static int t, n, k;
    public static String[] array;
    public static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            set = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            array = new String[n];
            String s = br.readLine();
            for (int i = 0; i < n; i++) {
                array[i] = s.substring(i, i + 1);
            }
            int m = n / 4;
            String tmp = "";
            for (int i = 0; i < m; i++) {
                for (int j = i; j < i + n; j++) {
                    if ((j - i) % m == 0 && j != i) {
                        set.add(tmp);
                        tmp = "";
                    }
                    tmp += array[j % n];
                }
                set.add(tmp);
                tmp = "";
            }
            List<Integer> list = new ArrayList<>();
            for (String s1 : set) {
                list.add(toDec(s1));
            }
            list.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            bw.write("#" + tc + " " + list.get(k - 1));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static Integer toDec(String s) {
        int result = 0;
        int cur = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'F') {
                result += cur * (s.charAt(i) - 'A' + 10);
            } else {
                result += cur * Integer.parseInt(s.substring(i, i + 1));
            }
            cur *= 16;
        }
        return result;
    }
}
