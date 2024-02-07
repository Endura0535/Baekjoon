import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static Point[][] map;
    static int R, C, N;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new Point[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                if (s.charAt(j) == '.')
                    map[i][j] = new Point(0, 0);
                else
                    map[i][j] = new Point(1, 0);
            }
        }

        for (int i = 2; i <= N; i++) {
            if (i % 2 == 0)
                fill(i);
            else
                boom(i);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j].x == 0)
                    System.out.print('.');
                else
                    System.out.print('O');
            }
            System.out.println();
        }
    }

    public static void boom(int t) {

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j].x == 1 && map[i][j].y == t - 3) {

                    map[i][j].x = 0;
                    map[i][j].y = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = j + dx[d];
                        int ny = i + dy[d];
                        if (canGo(nx, ny)) {
                            Point p = map[ny][nx];
                            if (p.x == 1 && p.y == t - 3)
                                continue;
                            p.x = 0;
                            p.y = 0;
                        }
                    }
                }
            }
        }

    }

    public static void fill(int t) {

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j].x == 0) {
                    map[i][j].x = 1;
                    map[i][j].y = t;
                }
            }
        }

    }

    public static boolean canGo(int x, int y) {
        return 0 <= x && x < C && 0 <= y && y < R;
    }

}