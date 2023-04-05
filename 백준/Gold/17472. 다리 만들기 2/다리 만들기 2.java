import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<ArrayList<Point>> Islands = new ArrayList<>();
	static int[][] Map;
	static int[][] connectCost;
	// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int N, M;
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Map = new int[N][M];
		connectCost = new int[101][101];
		isVisited = new boolean[N][M];

		// 섬 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				connectCost[i][j] = 101;
			}
		}

		// 섬 구분
		int islandNum = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (Map[i][j] == 1)
					numbering(j, i, islandNum++);
			}
		}

		connect();

		V = islandNum - 2;

		E = 0;
		for (int i = 2; i < V + 2; i++) {
			for (int j = 2; j < V + 2; j++) {
				if (connectCost[i][j] != 101 && connectCost[i][j] != 0)
					E++;
			}
		}
		//System.out.println(E);
		parents = new int[V];
		edgeList = new Edge[E];

		int cnt = 0;
		for (int i = 2; i < V + 2; i++) {
			for (int j = 2; j < V + 2; j++) {
				if (connectCost[i][j] != 101 && connectCost[i][j] != 0) {
					edgeList[cnt] = new Edge(i - 2, j - 2, connectCost[i][j]);
					cnt++;
				}
			}
		}

		make();

		// 간선비용이 작은 순서대로 정렬
		Arrays.sort(edgeList);
		int result = 0;
		int count = 0;// 연결 간선수
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) { // 싸이클이 발생하지 않았으면
				result += edge.weight;
				if (++count == V - 1) { // 연결 간선수가 정점수-1이면 다 연결한 것임.
					break;
				}

			}
		}

		if (count != V - 1)
			result = -1;
		System.out.println(result);

	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int start, int end, int weight) {
			super();
			this.from = start;
			this.to = end;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [start=" + from + ", end=" + to + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static Edge[] edgeList;
	static int[] parents;
	static int V, E;

	public static void make() {
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if (parents[a] == a)
			return a;// 자신이 루트이면 그냥 자신의 번호 리턴
		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;

		// 두 노드의 root가 다르면 합침
		parents[bRoot] = aRoot;
		return true;
	}

	// 섬에 번호 부여 -> BFS
	public static void numbering(int x, int y, int num) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		ArrayList<Point> island = new ArrayList<>();
		while (!q.isEmpty()) {
			Point p = q.poll();
			island.add(new Point(p.x, p.y));
			Map[p.y][p.x] = num;
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (canGo(nx, ny) && Map[ny][nx] == 1 && !isVisited[ny][nx]) {
					q.add(new Point(nx, ny));
					isVisited[ny][nx] = true;
				}
			}
		}
		Islands.add(island);
	}

	public static void connect() {
		int count = 2;
		for (ArrayList<Point> arr : Islands) {
			for (int i = 0; i < arr.size(); i++) {
				Point p = arr.get(i); // 시작 탐색 지점
				for (int j = 0; j < 4; j++) {
					int cost = 0;
					int nx = p.x;
					int ny = p.y;
					while (true) {
						nx += dx[j];
						ny += dy[j];
						if (!canGo(nx, ny))
							break;
						if (Map[ny][nx] == count)
							break;
						if (Map[ny][nx] == 0)
							cost++;
						else {
							if (cost > 1) {
								int startI = Map[p.y][p.x];
								int endI = Map[ny][nx];
								connectCost[startI][endI] = Math.min(connectCost[startI][endI], cost);
								connectCost[endI][startI] = connectCost[startI][endI];
							}
							break;
						}
					}
				}
			}
			count++;
		}
	}

	// 갈 수 있는지 확인
	public static boolean canGo(int x, int y) {
		if (x >= 0 && x < M && y >= 0 && y < N)
			return true;
		return false;
	}

}