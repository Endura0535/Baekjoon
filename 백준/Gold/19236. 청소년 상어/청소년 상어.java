import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static class Fish implements Comparable<Fish>, Cloneable {
		int x, y, num, dir;

		public Fish(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public int compareTo(Fish o) {
			return this.num - o.num;
		}

		@Override
		protected Fish clone() throws CloneNotSupportedException {
			return (Fish) super.clone();
		}
	}

	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static int answer = 0;

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		// point.x -> 물고기 번호, point.y -> 물고기 방향 : -1이면 죽은 것.
		ArrayList<Fish> Fishes = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fishes.add(new Fish(0, 0, 0, -1)); // index를 맞춰주기 위해 아무 피시 객체를 하나 넣음
		
		int[][] map = new int[4][4];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int fishNum = Integer.parseInt(st.nextToken()); // 물고기 번호
				int fishDir = Integer.parseInt(st.nextToken()) - 1; // 물고기 방향
				map[i][j] = fishNum;
				Fishes.add(new Fish(j, i, fishNum, fishDir));
			}
		}

		Fishes.add(new Fish(0, 0, 17, Fishes.get(map[0][0]).dir));
		Collections.sort(Fishes);

		ArrayList<Fish> fishesCopy = new ArrayList<>();
		for (Fish f : Fishes) {
			fishesCopy.add(f.clone());
		}

		DFS(0, 0, map, fishesCopy, 0);
		System.out.println(answer);
	}

	public static int[][] copyMap(int[][] map) {
		int[][] mapCopy = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				mapCopy[i][j] = map[i][j];
			}
		}
		return mapCopy;
	}

	public static ArrayList<Fish> copyFishes(ArrayList<Fish> Fishes) throws CloneNotSupportedException {
		ArrayList<Fish> fishesCopy = new ArrayList<>();
		for (Fish f : Fishes) {
			fishesCopy.add(f.clone());
		}
		return fishesCopy;
	}

	public static int eat(int sharkX, int sharkY, int[][] mapCopy, ArrayList<Fish> fishesCopy) {
		int fishNum = mapCopy[sharkY][sharkX];
		fishesCopy.get(17).dir = fishesCopy.get(mapCopy[sharkY][sharkX]).dir; // 상어 위치 먹은 물고기로 변경
		fishesCopy.get(fishNum).dir = -1; // 먹힌 물고기는 방향 -1로
		mapCopy[fishesCopy.get(17).y][fishesCopy.get(17).x] = 0;
		mapCopy[sharkY][sharkX] = 17;
		fishesCopy.get(17).x = sharkX;
		fishesCopy.get(17).y = sharkY;

		return fishNum;
	}

	public static void fishMove(int sharkX, int sharkY, int[][] mapCopy, ArrayList<Fish> fishesCopy) {
		// 물고기 이동
		for (Fish f : fishesCopy) {
			if (f.dir != -1 && f.num != 0 && f.num != 17) { // 이미 죽은 물고기가 아니면
				int rotateCnt = 0;
				while (rotateCnt < 8) {
					int nx = f.x + dx[f.dir];
					int ny = f.y + dy[f.dir];
					if (canGo(nx, ny)) { //경계체크
						if (nx != sharkX || ny != sharkY) { // 가려고 하는 곳에 상어가 있지 않으면 자리 바꾸기
							int temp = mapCopy[ny][nx];
							int tx = f.x;
							int ty = f.y;
							mapCopy[ny][nx] = mapCopy[f.y][f.x];
							mapCopy[f.y][f.x] = temp;
							f.x = nx;
							f.y = ny;
							fishesCopy.get(temp).x = tx;
							fishesCopy.get(temp).y = ty;
							break;
						}
					}
					f.dir = (f.dir + 1) % 8; // 못가면 방향 변경해서 다시 시도
				}
			}
		}
	}
	
	public static void DFS(int sharkX, int sharkY, int[][] map, ArrayList<Fish> Fishes, int count)
			throws CloneNotSupportedException {

		int[][] mapCopy = copyMap(map); 						// 맵 복사
		ArrayList<Fish> fishesCopy = copyFishes(Fishes); 		// 배열 복사
		
		count += eat(sharkX, sharkY, mapCopy, fishesCopy);		// 이동한 칸의 물고기 먹기
		fishMove(sharkX, sharkY, mapCopy, fishesCopy);			// 물고기 이동

		// 상어 이동 -> 재귀 호출
		for (int i = 1; i <= 3; i++) {
			int nx = sharkX + dx[fishesCopy.get(17).dir] * i;
			int ny = sharkY + dy[fishesCopy.get(17).dir] * i;
			if (!canGo(nx, ny)) {
				answer = Math.max(answer, count);
				return;
			}
			if (fishesCopy.get(mapCopy[ny][nx]).dir == -1 && fishesCopy.get(mapCopy[ny][nx]).num == 0)
				continue;
			DFS(nx, ny, mapCopy, fishesCopy, count);
		}

	}

	public static boolean canGo(int x, int y) {
		if (x < 0 || y < 0 || x >= 4 || y >= 4)
			return false;
		return true;
	}

}