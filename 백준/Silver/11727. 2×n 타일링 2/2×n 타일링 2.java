import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	
	// n별 개수를 저장할 정답 배열 선언
	static BigInteger[] tiling;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// N까지 입력받기 위해 N+1 크기로 초기화
		tiling = new BigInteger[N + 1];
		// 초기값 입력
		tiling[1] = BigInteger.ONE;
		// tiling 배열 채우기
		for (int i = 2; i <= N; i++) {
			// 짝수일 경우
			if (i % 2 == 0)
				tiling[i] = tiling[i - 1].multiply(BigInteger.TWO).add(BigInteger.ONE);
			// 홀수일 경우
			else
				tiling[i] =tiling[i - 1].multiply(BigInteger.TWO).subtract(BigInteger.ONE);
		}

		System.out.println(tiling[N].mod(new BigInteger("10007")));
	}
	
}