import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 카드 묶음의 수 (1 ≤ N ≤ 100,000)
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			// 카드 묶음 크기(1,000보다 작거나 같은 양의 정수) 저장
			pq.add(Integer.parseInt(br.readLine()));
		}

		// 카드 더미가 1개이면 섞는 횟수 0 출력 후 종료
		if (pq.size() == 1) {
			System.out.println(0);
			return;
		}
		// 최소 비교 횟수
		int answer = 0;

		// 카드 더미가 하나로 합쳐질 때까지
		while (pq.size() != 1) {
			// 가장 적은 수 2개 뽑아서
			int a = pq.poll();
			int b = pq.poll();
			// 더하고 pq에 다시 추가
			pq.add(a + b);
			// 비교 횟수 추가
			answer += a + b;
		}

		System.out.println(answer);

	}

}