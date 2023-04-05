import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, d, k, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int[] sushi = new int[N];
		int[] selected = new int[d + 1];

		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		int count = 0;
		for (int i = 0; i < k; i++) {
			if (selected[sushi[i]] == 0) {
				count++;
			}

			selected[sushi[i]]++;
		}

		int answer = count;

		for (int i = 1; i < N; i++) {

			if (answer <= count) {
				// 쿠폰 초밥을 안먹었으면 쿠폰 초밥을 먹을 수 있으므로 +1
				if (selected[c] == 0) {
					answer = count + 1;
				} else {
					answer = count;
				}
			}

			int end = (i + k - 1) % N;
			if (selected[sushi[end]] == 0) {
				count++;
			}
			selected[sushi[end]]++;

			selected[sushi[i - 1]]--;
			if (selected[sushi[i - 1]] == 0) {
				count--;
			}
		}

		System.out.println(answer);

	}

}