import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][M];
        for (int n = 0; n < N; n++) {
            int[][] plus = new int[M][M];
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[2 * N - 1];
            int x = 0;
            int y = M - 1;
            for (int i = 0; i < 3; i++) {
                int cnt = Integer.parseInt(st.nextToken());
                for (int j = 0; j < cnt; j++) {
                    plus[y][x] = i;
                    if (y == 0)
                        x++;
                    else y--;
                }
            }

            for (int y2 = 1; y2 < M; y2++) {
                for (int x2 = 1; x2 < M; x2++) {
                    plus[y2][x2] = Math.max(plus[y2 - 1][x2], plus[y2][x2 - 1]);
                }
            }

            for (int x2 = 0; x2 < M; x2++) {
                for (int y2 = 0; y2 < M; y2++) {
                    map[y2][x2] += plus[y2][x2];
                }
            }
        }

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                System.out.print(map[y][x] + 1 + " ");
            }
            System.out.println();
        }
    }
}
