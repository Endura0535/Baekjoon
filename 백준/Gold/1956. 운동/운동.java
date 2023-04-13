import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int[][] map = new int[V][V];
		for (int i = 0; i < V; i++) {
			Arrays.fill(map[i], 4000001);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[a - 1][b - 1] = Math.min(map[a - 1][b - 1], c);
		}

		// 플로이드-워셜
		for (int w = 0; w < V; w++) {
			for (int s = 0; s < V; s++) {
				for (int e = 0; e < V; e++) {
					map[s][e] = Math.min(map[s][e], map[s][w] + map[w][e]);
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		
		// 자기 자신으로 돌아오는 경로의 비용 중 최소 값 탐색
		for (int i = 0; i < V-1; i++) {
			answer = Math.min(answer, map[i][i]);
		}
		
		if(answer == 4000001)				// 갈 수 없으면 -1 출력
			System.out.println(-1);
		else
			System.out.println(answer);
	}

}