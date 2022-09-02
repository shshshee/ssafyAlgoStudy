import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767 {

	private static int N;
	private static boolean[][] map;
	private static ArrayList<Core> coreDirArr; //방향을 고려한 각각의 코어에 대한 배열
	private static int MaxCoreCnt;
	private static int ans;
	private static boolean[] coreDirCheck;
	private static boolean[] coreCheck;
	private static int size;

	public static class Core{
		int x, y, length, numbering, dir; //length: 해당 dir방향의 길이, numbering: 코어에 대한 넘버링
		//dir: 4가지 방향 (상 하 좌 우)
		public Core(int x, int y, int length, int numbering, int dir) {
			this.x = x;
			this.y = y;
			this.length = length;
			this.numbering = numbering;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new boolean[N][N];
			int numbering =0;

			coreDirArr = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp == 1) {
						if(i == 0 || j == 0 || i == N-1 || j == N-1) continue;
						map[i][j] = true;
						coreDirArr.add(new Core(i, j, i, numbering, 1)); //상
						coreDirArr.add(new Core(i, j, N-1-i, numbering, 2)); //하
						coreDirArr.add(new Core(i, j, j, numbering, 3)); //좌
						coreDirArr.add(new Core(i, j, N-1-j, numbering, 4)); //우
						numbering++;
					}
				}
			}
			size =coreDirArr.size();
			coreDirCheck = new boolean[size]; // 방향을 고려한 코어의 체크 배열
			coreCheck = new boolean[numbering+1]; //해당 numbering의 코어 체크 배열 
				
			MaxCoreCnt =Integer.MIN_VALUE;	//코어의 개수 최대값 비교 변수
			ans = Integer.MAX_VALUE;//전선의 길이의 최소값
			subset(0);
			System.out.println("#" + tc + " " + ans);
		}//입력완료
	}
	public static void subset(int idx) {
		if(idx == size) {
			int length = 0;
			int coreCnt = 0;
			for(int i = 0; i < size; i++) {
				if(coreDirCheck[i]) {
					Core tmpC =coreDirArr.get(i);
					length+=tmpC.length;
					coreCnt++;
				}
			}
			if(coreCnt > MaxCoreCnt) {
				MaxCoreCnt = coreCnt;
				ans = length;
			}else if(coreCnt == MaxCoreCnt) {
				ans = Math.min(ans, length);
			}
			
			return;
		}
		Core c = coreDirArr.get(idx);
		//뽑는 경우
		if(!coreCheck[c.numbering]) { //해당 넘버의 코어가 선택되지 않았다면
			if(check(c.x, c.y, c.dir)) {//해당 코어의 방향이 선택 가능하다면
				coreCheck[c.numbering] = true;
				coreDirCheck[idx] = true;
				
				boolean[][] tmp = new boolean[N][N];
                
				for(int i =0 ; i < N; i++) {
					for(int j = 0; j <N; j++) {
						tmp[i][j] = map[i][j];
					}
				}
				switch (c.dir) {
				case 1: //상
					for(int i = 0; i < c.x; i++) {
						map[i][c.y] = true;
					}
					break;
				case 2://하
					for(int i = c.x+1; i < N; i++) {
						map[i][c.y] = true;
					}
					break;
				case 3://좌
					for(int j = 0; j < c.y; j++) {
						map[c.x][j] = true;
					}
					break;
				case 4:
					for(int j = c.y+1; j < N; j++) {
						map[c.x][j] = true;
					}
					break;
				}
				subset(idx + 1);
				//원상복귀
				coreCheck[c.numbering] = false;
				coreDirCheck[idx] = false;
				for(int i =0 ; i < N; i++) {
					for(int j = 0; j <N; j++) {
						 map[i][j]= tmp[i][j];
					}
				}
			}
		}
		//안뽑는경우
		subset(idx+1);
	}
	private static boolean check(int x, int y, int dir) {
		switch (dir) {
		case 1: //상
			for(int i = 0; i < x; i++) {
				if(map[i][y] == true) return false;
			}
			return true;
		case 2: //하
			for(int i = x+1; i < N; i++) {
				if (map[i][y] == true) return false;
			}
			return true;
		case 3: //좌
			for(int j = 0; j < y; j++) {
				if(map[x][j] == true) return false;
			}
			return true;
		case 4: //우
			for(int j = y +1; j < N; j++) {
				if(map[x][j] == true) return false;
			}
			return true;
		}
		return false;
	}
}
