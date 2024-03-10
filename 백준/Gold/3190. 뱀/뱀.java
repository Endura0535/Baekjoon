import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Rotate {

        int day;
        char dir;

        public Rotate(int day, char dir) {
            this.day = day;
            this.dir = dir;
        }

    }

    static int[][] map;
    static int N, K;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 2][N + 2];
        map[1][1] = 1;
        for (int i = 0; i < N + 2; i++) {
            map[0][i] = 1;
            map[N + 1][i] = 1;
            map[i][0] = 1;
            map[i][N + 1] = 1;
        }
        Deque<Point> snake = new ArrayDeque<>();
        snake.add(new Point(1, 1));
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = 2;
        }
        int rotate = Integer.parseInt(br.readLine());
        Queue<Rotate> rotateQueue = new LinkedList<>();
        for (int i = 0; i < rotate; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            rotateQueue.add(new Rotate(day, dir));
        }

        int direction = 0;
        int day = 1;
        while (true) {
            if (!rotateQueue.isEmpty()) {
                if (day == rotateQueue.peek().day + 1) {
                    char dir = rotateQueue.poll().dir;
                    if (dir == 'D') {
                        direction = (direction + 1) % 4;
                    } else
                        direction = (direction + 3) % 4;
                }
            }
            int nx = snake.peekFirst().x + dx[direction];
            int ny = snake.peekFirst().y + dy[direction];
            int next = map[ny][nx];
            if (next == 1) {
                System.out.println(day);
                return;
            } else {
                snake.addFirst(new Point(nx, ny));
                map[ny][nx] = 1;
                if (next == 0) {
                    Point p = snake.pollLast();
                    map[p.y][p.x] = 0;
                }
            }
            day++;
        }

    }

}