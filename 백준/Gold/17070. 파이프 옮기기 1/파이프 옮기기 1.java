import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static class Pipe {
		// 파이프 끝의 x좌표, y좌표, 놓여진 상태(0: 가로, 1: 세로, 2: 대각선)
		int x, y, status;
	}

	static int[] dx = { 1, 0, 1 };
	static int[] dy = { 0, 1, 1 };

	static int[][] map;
	static int N, answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		map = new int[N+2][N+2];
		
		for (int i = 0; i < N+2; i++) {
			Arrays.fill(map[i], -1);
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(2, 1, 0);

		System.out.println(answer);

	}

	private static void DFS(int x, int y, int status) {
		if (x == N && y == N) {
			answer++;
			return;
		}
		for (int i = 0; i < 3; i++) {
			if(status == 0 && i == 1)
				continue;
			if(status == 1 && i == 0)
				continue;
			if (canGo(x, y, i)) {
				DFS(x + dx[i], y + dy[i], i);
			}
		}
	}

	private static boolean canGo(int x, int y, int move) {
		switch (move) {
		case 0:
			if (map[y][x + 1] == 0)
				return true;
			else
				return false;
		case 1:
			if (map[y + 1][x] == 0)
				return true;
			else
				return false;
		case 2:
			if (map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0)
				return true;
			else
				return false;
		}
		return false;
	}

}