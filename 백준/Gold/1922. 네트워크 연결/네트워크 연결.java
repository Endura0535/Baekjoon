import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static class Edge implements Comparable<Edge> {
		int start, end, cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<Edge>[] edges = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edges[s].add(new Edge(s, e, c));
			edges[e].add(new Edge(e, s, c));
			
		}

		// 임의의 시작정점으로 1번 선택
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (Edge edge : edges[1]) {
			pq.add(edge);
		}

		// 방문체크 배열
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;

		int connect = 0;
		long cost = 0;
		while (connect < N - 1) {			// 프림 알고리즘
			int s = pq.size();
			for (int i = 0; i < s; i++) {
				Edge e = pq.poll();
				if (!visited[e.end]) {
					visited[e.end] = true;
					cost += e.cost;
					connect++;
					for (Edge edge : edges[e.end]) {
						pq.add(edge);
					}
					break;
				}
			}
		}
		
		System.out.println(cost);
	}
}