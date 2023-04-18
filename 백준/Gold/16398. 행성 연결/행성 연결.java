import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Edge implements Comparable<Edge>{

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
		
		// 행성이 1개일때
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		ArrayList<Edge>[] edges = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				edges[i].add(new Edge(i, j, Integer.parseInt(st.nextToken())));
			}
		}

		int connect = 0;
		long cost = 0;	// 연결된 간선 수, 연결한 비용
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 최초 임의의 정점으로 0번 선택
		for (Edge edge : edges[0]) {
			pq.add(edge);
		}
		
		boolean [] visited = new boolean[N];	// 방문 체크 배열
		visited[0] = true;
		
		// pq를 사용한 프림 알고리즘
		while (connect < N - 1) {
			int s = pq.size();
			for (int i = 0; i < s; i++) {
				Edge e = pq.poll();
				if(!visited[e.end]) {
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