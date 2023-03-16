import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 에너지 드링크 개수
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 에너지 드링크를 합친 양
		BigDecimal sum = BigDecimal.ZERO;
		int max = 0;
		int amount;
		// 에너지 드링크 양 입력
		for (int i = 0; i < N; i++) {
			amount = Integer.parseInt(st.nextToken());
			max = Math.max(max, amount);
			sum = sum.add(new BigDecimal(Integer.toString(amount)));
		}

		// 나머지 드링크는 부으면서 흘리므로 나누기 2 + 가장 큰 것은 안부으므로 그대로 더하기
		BigDecimal answer = sum.subtract(new BigDecimal(Integer.toString(max))).divide(new BigDecimal("2")).add(new BigDecimal(Integer.toString(max)));
		System.out.println(answer);

	}

}