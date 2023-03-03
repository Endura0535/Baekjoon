import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	public static class Core {
		// x좌표, y좌표
		int X, Y;
		// 상하좌우 연결 가능한지 여부
		boolean[] dir = new boolean[4];

		public Core(int x, int y) {
			super();
			X = x;
			Y = y;
		}

		public Core(int x, int y, boolean[] dir) {
			super();
			X = x;
			Y = y;
			this.dir = dir;
		}
	}

	// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	// 맵 길이
	static int N;
	// 연결된 코어 수
	static int connectedCores;
	// 연결된 선의 총 길이
	static int lineLen;

	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 맵 길이

			map = new int[N][N];

			StringTokenizer st;

			ArrayList<Core> cores = new ArrayList<>();

			// 맵 입력
			for (int r = 0; r < N; r++) {
				connectedCores = 0;
				lineLen = Integer.MAX_VALUE;

				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					// core일때
					if (map[r][c] == 1) {
						// 외곽에 있는 이미 연결된 코어가 아니면
						if (r != 0 && c != 0 && r != N - 1 && c != N - 1) {
							// 상하좌우 연결할 수 있는지 탐색 후 배열에 넣기
							cores.add(new Core(c, r));
						}
					}

				}
			}

			for (Core c : cores) {
				c.dir = new boolean[] { canConnect(map, c.Y, c.X, 0), canConnect(map, c.Y, c.X, 1),
						canConnect(map, c.Y, c.X, 2), canConnect(map, c.Y, c.X, 3) };
			}

			int[][] mapCopy = new int[N][N];

			copy(map, mapCopy);

			DFS(cores, 0, 0, 0, mapCopy);

			System.out.println("#" + t + " " + lineLen);
		}
	}

	private static boolean canConnect(int[][] map, int r, int c, int d) {

		int nx = c + dx[d];
		int ny = r + dy[d];

		while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
			if (map[ny][nx] == 1)
				return false;
			nx += dx[d];
			ny += dy[d];
		}
		return true;
	}

	private static void copy(int[][] map2, int[][] mapCopy) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				mapCopy[i][j] = map2[i][j];
			}
		}
	}

	private static void DFS(ArrayList<Core> cores, int index, int lineLenSum, int connectedCoresSum, int[][] mapCopy) {

		if (index == cores.size()) {

			if (connectedCoresSum == connectedCores) {
				lineLen = Math.min(lineLenSum, lineLen);
			} else if (connectedCoresSum > connectedCores) {
				lineLen = lineLenSum;
				connectedCores = connectedCoresSum;
			}

			return;
		}

		// 연결하지 않는 경우
		DFS(cores, index + 1, lineLenSum, connectedCoresSum, mapCopy);

		Core c = cores.get(index);

		// 연결할 수 있는 사방 탐색
		for (int i = 0; i < 4; i++) {
			if (c.dir[i]) {
				int[][] mapCopyCopy = new int[N][N];
				copy(mapCopy, mapCopyCopy);
				int con = connect(mapCopyCopy, c.Y, c.X, i);
				if (con != -1) {
					DFS(cores, index + 1, lineLenSum + con, connectedCoresSum + 1, mapCopyCopy);
				}
			}
		}

	}

	// 연결
	private static int connect(int[][] mapCopyCopy, int r, int c, int d) {

		int nx = c + dx[d];
		int ny = r + dy[d];
		int count = 0;

		while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
			if (mapCopyCopy[ny][nx] == 1)
				return -1;
			mapCopyCopy[ny][nx] = 1;
			count++;
			nx += dx[d];
			ny += dy[d];
		}
		return count;
	}

}