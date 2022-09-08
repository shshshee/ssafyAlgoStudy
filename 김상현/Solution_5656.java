package etc;

import java.io.*;
import java.util.*;

public class Solution_5656 {

    public static int t;
    public static int n, w, h;
    public static int[][] board;
    public static int[][] copied;
    public static int[] array;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int total;
    public static int cnt;
    public static int mx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            total = 0;
            mx = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            board = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] != 0) {
                        total++;
                    }
                }
            }
            array = new int[n];
            recur(0);
            bw.write("#" + tc + " " + (total - mx));
            bw.newLine();
        }
        bw.flush();
    }

    private static void recur(int cur) {
        if (cur == n) {
            cnt = 0;
            copied = new int[h][w];
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    copied[j][k] = board[j][k];
                }
            }
            for (int i = 0; i < n; i++) {
                shoot(array[i]);
            }
            mx = Math.max(mx, cnt);
            return;
        }
        for (int i = 0; i < w; i++) {
            array[cur] = i;
            recur(cur + 1);
        }
    }

    private static void shoot(int x) {
        int a = -1;
        int b = -1;
        for (int i = 0; i < h; i++) {
            if (copied[i][x] != 0) {
                a = i;
                b = x;
                break;
            }
        }
        if (a != -1) {
            Queue<int[]> q = new LinkedList<>();
            boolean visited[][] = new boolean[h][w];
            q.add(new int[]{a, b});
            visited[a][b] = true;
            while (!q.isEmpty()) {
                int cx = q.peek()[0];
                int cy = q.peek()[1];
                q.poll();
                if (copied[cx][cy] == 0) {
                    continue;
                }
                int cd = copied[cx][cy];
                cnt++;
                copied[cx][cy] = 0;
                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j < cd; j++) {
                        int nx = cx + dx[i] * j;
                        int ny = cy + dy[i] * j;
                        if (inRange(nx, ny) && !visited[nx][ny]) {
                            q.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }

            }
        }
        down();
    }

    private static void down() {
        for (int j = 0; j < w; j++) {
            int idx = h - 1;
            for (int i = h - 1; i >= 0; i--) {
                if (copied[i][j] != 0) {
                    copied[idx][j] = copied[i][j];
                    if(idx != i){
                        copied[i][j] = 0;
                    }
                    idx--;
                }
            }
        }
    }

    private static boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < h && ny >= 0 && ny < w;
    }
}