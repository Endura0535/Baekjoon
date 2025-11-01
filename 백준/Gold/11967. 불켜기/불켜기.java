import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Map<Point, List<Point>> switches;
    static boolean[][] lightOn, visited;
    static int answer = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // N x N 개의 방
        int M = Integer.parseInt(st.nextToken());   // 다음 M줄에는 네 개의 정수 x, y, a, b가 주어진다. (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다는 의미

        switches = new HashMap<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switches.computeIfAbsent(new Point(x, y), k -> new ArrayList<>()).add(new Point(a, b));
        }
        lightOn = new boolean[N + 1][N + 1];
        lightOn[1][1] = true;
        visited = new boolean[N + 1][N + 1];
//        Map<Integer, List<Integer>> canGo = new HashMap<>();
//        canGo.computeIfAbsent(1, k -> new ArrayList<>()).add(1);

        boolean flag = true;
        Deque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(1, 1));
        boolean newPoint = true;
        do {
            flag = false;
            int size = deque.size();
            for (int s = 0; s < size; s++) {
                Point point = deque.poll();
                if (!canReach(point.x, point.y)) {
                    deque.addLast(point);
                    continue;
                }
                if (!switches.containsKey(point))
                    continue;
                for (Point swit : switches.get(point)) {
                    if (lightOn[swit.y][swit.x])
                        continue;
                    flag = true;
                    lightOn[swit.y][swit.x] = true;
                    deque.addLast(new Point(swit.x, swit.y));
                }
            }
        } while (flag);

        cal();
//        System.out.println(deque);
//        System.out.println(Arrays.deepToString(lightOn));
        System.out.println(answer);
    }

    static void cal() {
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (lightOn[y][x])
                    answer++;
            }
        }
    }

    static boolean canReach(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[y][x] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Point point = queue.poll();
                if (point.x == 1 && point.y == 1)
                    return true;
                for (int d = 0; d < 4; d++) {
                    int nx = point.x + dx[d];
                    int ny = point.y + dy[d];
                    if (nx < 0 || ny < 0 || nx > N || ny > N)
                        continue;
                    if (!visited[ny][nx] && lightOn[ny][nx]) {
                        queue.add(new Point(nx, ny));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return false;
    }

}
