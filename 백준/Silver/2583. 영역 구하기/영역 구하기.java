import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static int M, N, K;

    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int leftX = Integer.parseInt(st.nextToken());
            int leftY = Integer.parseInt(st.nextToken());
            int rightX = Integer.parseInt(st.nextToken());
            int rightY = Integer.parseInt(st.nextToken());

            for (int x = leftX; x < rightX; x++) {
                for (int y = leftY; y < rightY; y++) {
                    map[y][x] = 1;
                }
            }
        }

        int count = 0;
        ArrayList<Integer> areaList = new ArrayList<>();

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == 0 && !visited[y][x]) {
                    visited[y][x] = true;
                    areaList.add(BFS(x, y));
                    count++;
                }
            }
        }

        Collections.sort(areaList);

        System.out.println(count);
        for(int a : areaList){
            System.out.print(a + " ");
        }

    }

    public static boolean canGo(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static int BFS(int x, int y) {

        int area = 0;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            int size = queue.size();
            area += size;
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (canGo(nx, ny)) {
                        if (map[ny][nx] == 0 && !visited[ny][nx]) {
                            queue.add(new Point(nx, ny));
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
        }

        return area;

    }

}