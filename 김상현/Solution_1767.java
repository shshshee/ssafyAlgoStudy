import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static int t, n;
    public static int[][] board;
    public static int[][] copied;
    public static List<int[]> cores;
    public static int mx;
    public static int mn;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[] array;
    public static int[] array2;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            mx = 0;
            mn = Integer.MAX_VALUE;
            n = Integer.parseInt(br.readLine());
            cores = new ArrayList<>();
            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1 && i != 0 && i != n - 1 && j != 0 && j != n - 1) {
                        cores.add(new int[]{i, j});
                    }
                }
            }
            array = new int[cores.size()];
            array2 = new int[cores.size()];
            visited = new boolean[cores.size()];
            recur(0, 0);
            bw.write("#" + tc + " " + mn);
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void recur(int cur, int start) {
        if (cur != 0) {
            boolean flag = check(cur);;
            if (!flag) {
                return;
            }
        }
        if (cores.size() - start + 1 + cur < mx) {
            return;
        }
        for (int i = start; i < cores.size(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            array[cur] = i;
            for (int j = 0; j < 4; j++) {
                array2[cur] = j;
                recur(cur + 1, i + 1);
            }
            visited[i] = false;
        }
    }

    private static boolean check(int cur) {
        int sum = 0;
        copied = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copied[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < cur; i++) {
            int cnt = connect(array[i], array2[i]);
            if (cnt == -1) {
                return false;
            }
            sum += cnt;
        }
        if (cur > mx) {
            mx = cur;
            mn = sum;
        } else if (cur == mx) {
            if (mn > sum) {
                mn = sum;
            }
        }
        return true;
    }

    private static int connect(int idx, int dir) {
        int cnt = 0;
        int x = cores.get(idx)[0];
        int y = cores.get(idx)[1];
        while (true) {
            x += dx[dir];
            y += dy[dir];
            if (!inRange(x, y)) {
                break;
            }
            if (copied[x][y] == 1) {
                return -1;
            }
            cnt++;
        }
        x = cores.get(idx)[0];
        y = cores.get(idx)[1];
        while (inRange(x, y)) {
            copied[x][y] = 1;
            x += dx[dir];
            y += dy[dir];
        }
        return cnt;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
