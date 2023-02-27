import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int comNum;
	static int[][] connection;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		comNum = Integer.parseInt(br.readLine());
		int con = Integer.parseInt(br.readLine());

		connection = new int[comNum][comNum];
		visited = new boolean[comNum];

		StringTokenizer st;

		int com1, com2;
		for (int i = 0; i < con; i++) {
			st = new StringTokenizer(br.readLine());
			com1 = Integer.parseInt(st.nextToken());
			com2 = Integer.parseInt(st.nextToken());

			connection[com1 - 1][com2 - 1] = 1;
			connection[com2 - 1][com1 - 1] = 1;
		}

		BFS();

	}

	public static void BFS() {

		Set<Integer> set = new HashSet<>();
		set.add(0);
		Queue<Integer> q = new LinkedList<>();

		q.add(0);
		int cNum;
		while (!q.isEmpty()) {
			cNum = q.poll();
			for (int i = 0; i < comNum; i++) {
				if (connection[cNum][i] == 1) {
					if (!visited[i]) {
						visited[i] = true;
						q.add(i);
						set.add(i);
					}
				}
			}
		}

		System.out.println(set.size() - 1);
	}

}