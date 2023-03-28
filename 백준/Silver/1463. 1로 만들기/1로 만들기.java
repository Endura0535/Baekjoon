import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		Queue<Integer> q = new LinkedList<>();

		q.add(N);
		int count = 0;
		while (!q.isEmpty()) {
			int s = q.size();
			for (int i = 0; i < s; i++) {
				int t = q.poll();
				if (t == 1) {
					System.out.println(count);
					return;
				}
				if (t % 3 == 0)
					q.add(t / 3);
				if (t % 2 == 0)
					q.add(t / 2);
				q.add(t - 1);
			}
			count++;
		}
	}

}