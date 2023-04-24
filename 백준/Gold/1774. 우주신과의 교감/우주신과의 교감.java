import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static int parents[];

	public static void makeSet(int v) {
		parents[v] = v;
	}

	public static int findSet(int v) {
		if (parents[v] == v)
			return v;
		return findSet(parents[v]);
	}

	public static void union(int v, int u) {
		int rV = findSet(v);
		int rU = findSet(u);
		if (rV < rU)
			parents[rU] = rV;
		else
			parents[rV] = rU;
	}

	public static class Edge implements Comparable<Edge> {
		int start, end;
		double cost;

		public Edge(int start, int end, double cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 우주신들의 개수
		int M = Integer.parseInt(st.nextToken()); // 이미 연결된 신들과의 통로의 개수

		Point[] coor = new Point[N + 1]; // 우주신 좌표 배열
		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) { // 좌표값 입력, parents 배열 초기화
			st = new StringTokenizer(br.readLine());
			coor[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			makeSet(i);
		}

		PriorityQueue<Edge> edges = new PriorityQueue<>();

		for (int i = 1; i < N; i++) { // 간선 입력
			for (int j = i + 1; j <= N; j++) {
				double cost = Math.sqrt(Math.pow((coor[i].x - coor[j].x), 2) + Math.pow(coor[i].y - coor[j].y, 2));
				edges.add(new Edge(i, j, cost));
				edges.add(new Edge(j, i, cost));
			}
		}

		int connect = 0;

		for (int i = 0; i < M; i++) { // 이미 연결된 통로 입력
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (findSet(s) != findSet(e)) {
				connect++;
			}
			union(s, e);
		}
		
		double cost = 0;

		while (connect < N - 1) {
			Edge e = edges.poll();
			if (findSet(e.start) != findSet(e.end)) {
				union(e.start, e.end);
				union(e.end, e.start);
				cost += e.cost;
				connect++;
			}
		}

		System.out.printf("%.2f", cost);
	}

}