import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	// 40보다 작거나 같은 피보나치 0,1 개수 카운트를 저장해줄 배열 생성
	static int[][] memo = new int[41][2];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		int N;

		// 피보나치 기록 배열 초기화 -> 연산 안했을 경우 -1로 초기화
		for (int i = 0; i <= 40; i++) {
			memo[i][0] = -1;
			memo[i][1] = -1;
		}
		// N = 0,1일때 초기화
		memo[0][0] = 1;
		memo[0][1] = 0;
		memo[1][0] = 0;
		memo[1][1] = 1;

		// N = 2 ~ 40까지 저장
		for (int i = 2; i <= 40; i++) {
			memo[i][0] = memo[i - 1][0] + memo[i - 2][0];
			memo[i][1] = memo[i - 1][1] + memo[i - 2][1];
		}

		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < TC; t++) {
			N = Integer.parseInt(br.readLine());
			// 답 추가
			sb.append(memo[N][0]).append(" ").append(memo[N][1]).append("\n");
		}

		System.out.println(sb);
	}

}