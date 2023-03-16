import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long A = sc.nextInt();
		long B = sc.nextInt();
		
		Queue<Long> q = new LinkedList<Long>();
		int count = 1;
		// 초기값 입력
		q.add(A);
		// BFS 
		while(!q.isEmpty()) {
			int s = q.size();
			for (int i = 0; i < s; i++) {
				Long temp = q.poll();
				if(temp == B) {
					System.out.println(count);
					return;
				}
				// B보다 같거나 작은 경우에만 탐색할 필요가 있으므로 q에 넣음
				if(temp*2 <= B)
					q.add(temp*2);
				if(temp*10+1 <= B)
					q.add(temp*10+1);
			}
			count++;
		}
		
		System.out.println(-1);
	}
}