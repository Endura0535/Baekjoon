import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solution {

	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= tc; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] map = new int[N + 1][N + 1];

            for (int i = 0; i <= N; i++) {
                map[0][i] = 0;
                map[i][0] = 0;
            }

            for (int i = 1; i <= N; i++) {     // map 입력
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken()) + map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1];
                }
            }

            int answer = 0;

            for (int i = M; i <= N; i++) {
                for (int j = M; j <= N; j++) {
                    answer = Math.max(answer, map[i][j] - map[i][j - M] - map[i - M][j] + map[i - M][j - M]);
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

}