import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static int W, H;
	static int[][] map;
	static boolean[][] isVisted;
	static ArrayList<Integer> cheeseCounts = new ArrayList<>();
	// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static int nextX = 0, nextY = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		int cheeseCount = 0;

		map = new int[H][W];
		isVisted = new boolean[H][W];
		int sX, sY;

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					cheeseCount++;
			}
		}

		cheeseCounts.add(cheeseCount);

		int t = 0;

		// 2 : 겉의 공기, 3: 지워질 겉면
		while (cheeseCount != 0) {
			DFS(0, 0);

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					//System.out.print(map[i][j]);
					// 치즈 녹이기
					if (map[i][j] == 3) {
						map[i][j] = 0;
						cheeseCount--;
						nextX = j;
						nextY = i;
					}else if (map[i][j] == 2) {
						map[i][j] = 0;
					}
				}
				Arrays.fill(isVisted[i], false);
			}
			// 1시간 후의 치즈 개수 저장
			cheeseCounts.add(cheeseCount);
			t++;
		}

		System.out.println(t);
		System.out.println(cheeseCounts.get(cheeseCounts.size()-2));

	}

	/**
	 * 탐색을 통해 겉의 공기들 찾기. 공기와 인접한 겉면 겹치지 않게 배열에 넣기 겉면 녹여 없애기
	 * 
	 */

	// 겉의 공기들 찾기
	public static void DFS(int x, int y) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (canGo(nx, ny)) {
				if (map[ny][nx] == 1 || map[ny][nx] == 3) {
					map[y][x] = 2;
					map[ny][nx] = 3;
				}
				if (!isVisted[ny][nx] && map[ny][nx] == 0) {
					isVisted[ny][nx] = true;
					DFS(nx, ny);
				}
			}
		}
	}

	public static boolean canGo(int x, int y) {
		if (x < 0 || y < 0 || x >= W || y >= H)
			return false;
		return true;
	}

}