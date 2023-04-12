import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Vertex implements Comparable<Vertex> {
		int n, cost;

		public Vertex(int n, int cost) {
			this.n = n;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.cost - o.cost;
		}

	}

	public static class Edge implements Comparable<Edge> {
		int end, cost;

		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 간선 수
		int X = Integer.parseInt(st.nextToken()); // 파티 장소(도착지)

		boolean[] visited;

		ArrayList<Edge>[] routes = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			routes[i] = new ArrayList<>();
		}

		// 인접 리스트 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 출발지
			int end = Integer.parseInt(st.nextToken()); // 도착지
			int T = Integer.parseInt(st.nextToken()); // 소요 시간

			routes[start].add(new Edge(end, T));
		}

		int[] go = new int[N + 1]; // 파티에 가는데 걸리는 비용
		go[X] = 0;
		int[] back = new int[N + 1]; // 파티에서 돌아오는데 걸리는 비용
		Arrays.fill(back, Integer.MAX_VALUE);
		back[X] = 0;

		PriorityQueue<Vertex> vertexList = new PriorityQueue<>();

		// 가는 길 비용 찾기
		for (int i = 1; i <= N; i++) {
			int find = i;
			visited = new boolean[N + 1];
			int [] tempCost = new int[N+1];
			Arrays.fill(tempCost,Integer.MAX_VALUE);
			tempCost[i] = 0;
			while (!visited[find]) {
				visited[find] = true;
				for (Edge e : routes[find]) {
					if (!visited[e.end]) {
						if (tempCost[e.end] > tempCost[find] + e.cost) {
							tempCost[e.end] = tempCost[find] + e.cost;
							vertexList.add(new Vertex(e.end, tempCost[e.end]));
						}
					}
				}
				while (!vertexList.isEmpty()) {
					Vertex v = vertexList.poll();
					if (!visited[v.n]) {
						find = v.n;
						break;
					}
				}
			}
			go[i] = tempCost[X];
		}
		
		// 오는 길 비용 찾기
		int find = X;
		visited = new boolean[N + 1];
		while(!visited[find]) {
			visited[find] = true;
			for(Edge e:routes[find]) {
				if (!visited[e.end]) {
					if (back[e.end] > back[find] + e.cost) {
						back[e.end] = back[find] + e.cost;
						vertexList.add(new Vertex(e.end, back[e.end]));
					}
				}
			}
			while (!vertexList.isEmpty()) {
				Vertex v = vertexList.poll();
				if (!visited[v.n]) {
					find = v.n;
					break;
				}
			}
		}
		
		int [] totalCost = new int [N+1];
		
		int answer = 0;
		for (int j = 1; j <= N; j++) {
			totalCost[j] = go[j] + back[j];
			answer = Math.max(answer, totalCost[j]);
		}
				
		System.out.println(answer);

	}

}