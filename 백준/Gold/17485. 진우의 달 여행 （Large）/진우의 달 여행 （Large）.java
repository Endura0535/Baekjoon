import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[][][] costMap;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        costMap = new int[N][M][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int x = 0; x < M; x++) {
            for (int i = 0; i < 3; i++) {
                costMap[0][x][i] = map[0][x];
            }
        }

        for (int y = 1; y < N; y++) {
            for (int x = 0; x < M; x++) {
                for (int d = -1; d < 2; d++) {
                    int pre = Integer.MAX_VALUE;
                    int nx = x + d;
                    for (int i = 0; i < 3; i++) {
                        if (d == i - 1) continue;
                        if (nx >= 0 && nx < M) {
                            pre = Math.min(pre, costMap[y - 1][x + d][i]);
                        }
                    }
                    if (pre + map[y][x] < 0) costMap[y][x][d + 1] = Integer.MAX_VALUE;
                    else costMap[y][x][d + 1] = pre + map[y][x];
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int x = 0; x < M; x++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, costMap[N - 1][x][d]);
            }
        }

        System.out.println(answer);

    }

}