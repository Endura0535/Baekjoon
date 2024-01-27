import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];

		int maxHeight = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > maxHeight) {
					maxHeight = map[i][j];
				}
			}
		}

		int max = 0;
		
		// 모든 지역 탐색
		for (int height = 0; height < maxHeight + 1; height++) {
			isVisited = new boolean[n][n];
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 
					if (!isVisited[i][j] && map[i][j] > height) {
						// 해당 지역 안전 탐색
						if(dfs(i, j, height))
							cnt++;
					}
				}
			}
			max = Math.max(max, cnt);
		}
		System.out.println(max);
	}

	// DFS탐색
	static boolean dfs(int x, int y, int height) {
		isVisited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 경계 체크
			if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1)
				continue;
			// 방문 체크
			if (isVisited[nx][ny])
				continue;
			// 잠기지 않았으면 재귀호출
			if (map[nx][ny] > height) {
				dfs(nx, ny, height);
			}
		}
		return true;
	}
}