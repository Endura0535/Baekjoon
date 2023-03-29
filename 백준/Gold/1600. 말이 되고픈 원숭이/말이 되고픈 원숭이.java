import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Monkey {
		int x, y, horseCount;

		public Monkey(int x, int y, int horseCount) {
			this.x = x;
			this.y = y;
			this.horseCount = horseCount;
		}

		@Override
		public String toString() {
			return "Monkey [x=" + x + ", y=" + y + ", horseCount=" + horseCount + "]";
		}

	}

	public static class MapInfo {
		int move, horseCount;

		public MapInfo() {
			this.move = Integer.MAX_VALUE;
			this.horseCount = -1;
		}

		public MapInfo(int move, int horseCount) {
			this.move = move;
			this.horseCount = horseCount;
		}

	}

	static int K, W, H, answer = Integer.MAX_VALUE;
	static int[][] map;
	static MapInfo[][] mapinfo;
	static boolean[][] isVisited;

	static int[][] minMove;
	// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	// 말 행동 시계 방향
	static int[] hx = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] hy = { 2, 1, -1, -2, -2, -1, 1, 2 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		isVisited = new boolean[H][W];
		mapinfo = new MapInfo[H][W];
		minMove = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(minMove[i], Integer.MAX_VALUE);
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapinfo[i][j] = new MapInfo();
			}
		}
		mapinfo[0][0] = new MapInfo(-1, Integer.MAX_VALUE);

		BFS();
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		System.out.println(answer);
	}

	private static void BFS() {
		Monkey monkey = new Monkey(0, 0, K);
		Queue<Monkey> q = new LinkedList<>();
		q.add(monkey);
		int count = 0;
		while (!q.isEmpty()) {
			int s = q.size();
			for (int i = 0; i < s; i++) {
				Monkey m = q.poll();
				if (m.x == W - 1 && m.y == H - 1) {
					answer = count;
					return;
				}

				// 원숭이 이동
				for (int i1 = 0; i1 < 4; i1++) {
					int nx = m.x + dx[i1];
					int ny = m.y + dy[i1];
					if (canGo(nx, ny)) {
						// 이전보다 빨리 도착했거나 말 이동 개수가 많이 남아있으면
						// 이전보다 빨리 도착했거나 말 이동 개수가 많이 남아있으면
						if (mapinfo[ny][nx].move > count + 1 || m.horseCount > mapinfo[ny][nx].horseCount) {
							mapinfo[ny][nx].move = Math.min(mapinfo[ny][nx].move, count + 1);
							mapinfo[ny][nx].horseCount = Math.max(mapinfo[ny][nx].horseCount, m.horseCount);
							q.add(new Monkey(nx, ny, m.horseCount));
						}
					}
				}

				// 말 이동
				if (m.horseCount > 0) {
					for (int i1 = 0; i1 < 8; i1++) {
						int nx = m.x + hx[i1];
						int ny = m.y + hy[i1];
						if (canGo(nx, ny)) {
							// 이전보다 빨리 도착했거나 말 이동 개수가 많이 남아있으면
							if (mapinfo[ny][nx].move > count + 1 || m.horseCount - 1 > mapinfo[ny][nx].horseCount) {
								mapinfo[ny][nx].move = Math.min(mapinfo[ny][nx].move, count + 1);
								mapinfo[ny][nx].horseCount = Math.max(mapinfo[ny][nx].horseCount, m.horseCount - 1);
								q.add(new Monkey(nx, ny, m.horseCount - 1));
							}
						}
					}
				}
			}
			count++;
		}
		answer = -1;
	}

	private static boolean canGo(int x, int y) {
		if (x >= W || y >= H || x < 0 || y < 0 || map[y][x] == 1)
			return false;
		return true;
	}

}