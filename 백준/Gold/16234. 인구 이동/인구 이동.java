import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Queue<Point>> moveQ;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {

            BFS();
            if (moveQ.isEmpty()) {
                System.out.println(answer);
                return;
            }
            while (!moveQ.isEmpty()) {
                move(moveQ.poll());
            }
            answer++;
        }

    }

    public static void BFS() {

        moveQ = new LinkedList<>();
        Queue<Point> q = new LinkedList<>();

        visited = new boolean[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[y][x]) {
                    q.clear();
                    q.add(new Point(x, y));
                    visited[y][x] = true;
                    int s;
                    do {
                        s = q.size();
                        for (int i = 0; i < s; i++) {
                            Point p = q.poll();
                            for (int d = 0; d < 4; d++) {
                                int nx = dx[d] + p.x;
                                int ny = dy[d] + p.y;
                                if (canGo(nx, ny)) {
                                    if (!visited[ny][nx]) {
                                        int gap = Math.abs(map[ny][nx] - map[p.y][p.x]);
                                        if (L <= gap && gap <= R) {
                                            visited[ny][nx] = true;
                                            q.add(new Point(nx, ny));
                                        }
                                    }
                                }
                            }
                            q.add(p);
                        }
                    } while (q.size() != s);
                    if (q.size() != 1)
                        moveQ.add(new LinkedList<>(q));
                }
            }
        }

    }

    public static boolean canGo(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static void move(Queue<Point> queue) {

        int total = 0;
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Point p = queue.poll();
            total += map[p.y][p.x];
            queue.add(p);
        }

        for (int i = 0; i < size; i++) {
            Point p = queue.poll();
            map[p.y][p.x] = total / size;
        }

    }

}