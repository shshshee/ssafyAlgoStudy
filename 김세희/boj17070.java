import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17070 {
	static int N;
	static int[][] arr;
	static int[][] cntArr;
	static int x,y,d;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		cntArr = new int[N][N];
		
		x = 0;
		y = 1;
		d = 0; // 초기방향 가로
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(x,y,d);
		
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(cntArr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		System.out.println(cntArr[N-1][N-1]);
		
		
	}
	
	// 좌표, 방향 
	// 가로 : 0, 세로 : 1, 대각선 : 2
	static void dfs(int x,int y,int d) {
		
		cntArr[x][y]++; // 해당 좌표 도달했을 떄 카운팅
		
		// 가로일때 -> 가로, 대각선 이동 가능

		// 세로일때 -> 아래, 대각선 이동 가능
		
		// 대각선 -> 가로, 아래, 대각선 이동 가능 
		
		// 대각선으로 회전 or 이동하는 경우 -> 현재방향이 가로,세로,대각선일때
		if( d==0 || d==1 || d==2 ) {
			int nx = x+1;
			int ny = y+1;
			if(nx < N && ny < N && arr[nx][ny]==0 && arr[nx-1][ny]==0 && arr[nx][ny-1]==0) {
				dfs(nx,ny,2); // 대각선방향 이동 
			}
		}
		
		// 세로방향으로 이동하는 경우 -> 현재 방향이 세로, 대각선일떄
		if( d==1 || d==2 ) {
			int nx = x+1;
			int ny = y;
			if( nx < N && arr[nx][ny]==0 ) {
				dfs(nx,ny,1); // 세로방향 이동 
			}
		}
		
		if( d==0 || d==2 ) {
			int nx = x;
			int ny = y+1;
			if( ny < N && arr[nx][ny]==0 ) {
				dfs(nx,ny,0); // 가로방향 이동
			}
		}
		
	}

}