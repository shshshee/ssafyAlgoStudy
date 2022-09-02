package N9000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_9205_맥주걷기 {

	public static void main(String[] args) throws IOException {
		int beers = 20; // 한 박스에 20병 제한
		int term = 50; // 50m
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) { // Test Case
			// 1. 입력
			int n = Integer.parseInt(br.readLine()); // 편의점의 개수
			int[][] map = new int[n + 2][3]; // 지도

			for (int i = 0; i < map.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				if (i == 0)
					map[i][2] = 1; // 상근이 집
				else if (i == map.length - 1)
					map[i][2] = 2; // 페스티벌 위치
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}

			// 2. 직행 가능하면 바로 가기
			int dis1 = Distance(map[0][0], map[0][1], map[map.length - 1][0], map[map.length - 1][1]);
			if (dis1 <= beers * term) {
//				System.out.print(dis1 + " : ");
				System.out.println("happy");
				continue;
			}
			// 3. 편의점 들릴 경우
			else {
				// 3-1. 정렬 : 안 들려도 되는 편의점 제외
				Arrays.sort(map, new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						if (o1[0] == o2[0]) {
							return o1[1] - o2[1];
						}
						return o1[0] - o2[0];
					}
				});

				// 3-2. 지도에서 상근이 집과 페스티벌의 순서 찾기
				int homeI = 0; // 집 순서
				int fesI = 0; // 페스티벌 순서
				for (int i = 0; i < map.length; i++) {
					if (map[i][2] == 1)
						homeI = i;
					else if (map[i][2] == 2)
						fesI = i;
				}

				int sad = 0;
				boolean isSad = false;

				// 집에서 페스티벌 방향으로 걷기
				if (homeI <= fesI) {
					// 3-3. 현재위치~편의점까지 거리 중 50m 될 때마다 한 잔 드링킹
					for (int i = homeI; i < fesI; i++) {
						for (int j = i + 1; j <= fesI; j++) {
							int dis = Distance(map[i][0], map[i][1], map[j][0], map[j][0]);
							if (dis > beers * term) {
								sad++;
							} else {
								break;
							}
						}
						if (sad == map[i].length - i) {
							System.out.println("sad");
							sad = 0;
							isSad = true;
							break;
						}
					}
				} else {
					// 3-3. 현재위치~편의점까지 거리 중 50m 될 때마다 한 잔 드링킹
					for (int i = 0; i < map.length - 1; i++) {
						for (int j = i + 1; j < map.length; j++) {
							int dis = Distance(map[i][0], map[i][1], map[j][0], map[j][0]);
							if (dis > beers * term) {
								sad++;
							} else {
								break;
							}
						}
						if (sad == map[i].length - i) {
							System.out.println("sad");
							sad = 0;
							isSad = true;
							break;
						}
					}

				}

				if (isSad)
					continue;
				else {
					System.out.println("happy");
				}
			}
		} // =====> end of Test Case

	}

	static int Distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}
