import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int [][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(map[i], 20000001);
			map[i][i] = 0;
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[a-1][b-1] = Math.min(map[a-1][b-1], c);
		}

		for (int w = 0; w < n; w++) {					// 경유지
			for (int s = 0; s < n; s++) {				// 출발지
				if(s == w)
					continue;
				for (int e = 0; e < n; e++) {			// 도착지
					if(w == e)
						continue;
					map[s][e] = Math.min(map[s][e], map[s][w] + map[w][e]);
				}
			}
		}
		
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if(map[y][x] == 20000001)
					System.out.print(0);
				else
					System.out.print(map[y][x]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}