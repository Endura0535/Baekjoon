import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        long[][] reachMap = new long[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(reachMap[i], 0);
        }
        reachMap[0][0] = 1L;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if (n != 0) {
                    if (j + n < N)
                        reachMap[i][j + n] += reachMap[i][j];
                    if (i + n < N)
                        reachMap[i + n][j] += reachMap[i][j];
                }
            }
        }

        System.out.println(reachMap[N - 1][N - 1]);

    }

}