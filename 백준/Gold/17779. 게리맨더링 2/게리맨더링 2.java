import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	/**
	 * d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N
	 * 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
	 * 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
	 * 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
	 * 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
	 */

	static int N; // 재현시의 크기
	static int[][] Map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int totalCount = 0;
		// 입력 받기
		N = Integer.parseInt(br.readLine());
		Map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				totalCount += Map[i][j];
			}
		}

		int answer = Integer.MAX_VALUE;

		for (int d1 = 1; d1 < N - 1; d1++) {
			for (int d2 = 1; d2 < N - 1 - d1; d2++) {
				for (int x = 0; x < N; x++) {
					for (int y = 0; y < N; y++) {
						answer = Math.min(answer, calMinGap(x, y, d1, d2, totalCount));
					}
				}
			}
		}

		System.out.println(answer);

	}

	static int count = 1;

	static int calMinGap(int x, int y, int d1, int d2, int totalCount) {
		ArrayList<Integer> peopleCountList = new ArrayList<>();
		peopleCountList.add(cal1(x, y, d1, d2));
		peopleCountList.add(cal2(x, y, d1, d2));
		peopleCountList.add(cal3(x, y, d1, d2));
		peopleCountList.add(cal4(x, y, d1, d2));
		peopleCountList
				.add(totalCount - cal1(x, y, d1, d2) - cal2(x, y, d1, d2) - cal3(x, y, d1, d2) - cal4(x, y, d1, d2));
		return Collections.max(peopleCountList) - Collections.min(peopleCountList);
	}

	// 선거구1의 인구수 구하기
	static int cal1(int x, int y, int d1, int d2) {
		int people = 0;
		for (int r = 0; r < x + d1; r++) {
			for (int c = 0; c <= y; c++) {
				if (r + c == x + y)
					break;
				if (r < 0 || c < 0 || r >= N || c >= N)
					continue;
				people += Map[r][c];
			}
		}
		return people;
	}

	// 선거구2의 인구수 구하기
	static int cal2(int x, int y, int d1, int d2) {
		int people = 0;
		for (int r = 0; r <= x + d2; r++) {
			for (int c = N - 1; c > y; c--) {
				if (r - c == x - y)
					break;
				if (r < 0 || c < 0 || r >= N || c >= N)
					continue;
				people += Map[r][c];
			}
		}
		return people;
	}

	// 선거구3의 인구수 구하기
	static int cal3(int x, int y, int d1, int d2) {
		int people = 0;
		for (int r = N - 1; r >= x + d1; r--) {
			for (int c = 0; c < y - d1 + d2; c++) {
				if (r - c == x - y + (2 * d1))
					break;
				if (r < 0 || c < 0 || r >= N || c >= N)
					continue;
				people += Map[r][c];
			}
		}
		return people;
	}

	// 선거구4의 인구수 구하기
	static int cal4(int x, int y, int d1, int d2) {
		int people = 0;
		for (int r = N - 1; r > x + d2; r--) {
			for (int c = N - 1; c >= y - d1 + d2; c--) {
				if (r + c == x + y + (2 * d2))
					break;
				if (r < 0 || c < 0 || r >= N || c >= N)
					continue;
				people += Map[r][c];
			}
		}
		return people;
	}

}