import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj10026 {
	static int N;
	static char[][] arr1;
	static char[][] arr2;
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static class spot{
		int x;
		int y;
		spot(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr1 = new char[N][N];
		arr2 = new char[N][N];
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) {
				char color = str.charAt(j);
				arr1[i][j] = color;
				if(color == 'G') { // G와 R을 한 그룹으로 보기 때문에 G를 모두 R로 넣어준다.
					arr2[i][j] = 'R';
				}else arr2[i][j] = color;
				
			}
		}
		
		visited = new boolean[N][N];
		int cnt1 = 0;
		int cnt2 = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					bfs(i,j,arr1);
					cnt1++;
				}
			}
		}
		
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					bfs(i,j,arr2);
					cnt2++;
				}
			}
		}
		
		
		System.out.println(cnt1+" "+cnt2);
		
	}
	
	static void bfs(int x, int y, char[][] arr) {
		Queue<spot> queue = new LinkedList<>();
		queue.add(new spot(x,y));
		
		while(!queue.isEmpty()) {
			spot cur = queue.poll();
			for(int i=0;i<4;i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if(!visited[nx][ny] && arr[nx][ny] == arr[cur.x][cur.y]) {
						visited[nx][ny] = true;
						queue.add(new spot(nx,ny));
					}
				}
			}
		}
		
		
	}

}
