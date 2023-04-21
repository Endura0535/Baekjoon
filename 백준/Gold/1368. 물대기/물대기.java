import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge> {
		int end, cost;

		public Edge(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Edge [end=" + end + ", cost=" + cost + "]";
		}

	}

	static int N, totalCost = 0; // 땅 개수, 모든 논에 물을 대는데 필요한 최소비용
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Edge>[] edges = new PriorityQueue[N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			edges[i] = new PriorityQueue<>();
		}

		for (int i = 1; i <= N; i++) {
			int cost = Integer.parseInt(br.readLine());
			edges[i].add(new Edge(0, cost));
			edges[0].add(new Edge(i, cost));
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				edges[i].add(new Edge(j, Integer.parseInt(st.nextToken())));
			}
		}

		int current = 0; // 현재 지점을 물로 설정
		PriorityQueue<Edge> nextEdges = new PriorityQueue<>();
		for (Edge edge : edges[0]) {
			nextEdges.add(edge);
		}

		int wateredCnt = 0;

		while (wateredCnt < N) {
			Edge e = nextEdges.poll();
			if (e.end != 0 && !visited[e.end]) {
				totalCost += e.cost;
				visited[e.end] = true;
				for (Edge edge : edges[e.end]) {
					nextEdges.add(edge);
				}
				wateredCnt++;
			}
		}

		System.out.println(totalCost);

	}
}