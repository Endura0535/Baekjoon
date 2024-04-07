import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 2][N + 2];

        for (int i = 1; i <= N; i++) {
            String string = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(string.charAt(j-1)));
            }
        }

        boolean[][] visited = new boolean[N + 2][N + 2];

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(j, i));
                    int qSize = 0;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        qSize += size;
                        for (int k = 0; k < size; k++) {
                            Point p = queue.poll();
                            visited[p.y][p.x] = true;
                            for (int d = 0; d < 4; d++) {
                                int nx = p.x + dx[d];
                                int ny = p.y + dy[d];
                                if (!visited[ny][nx] && map[ny][nx] == 1) {
                                    queue.add(new Point(nx, ny));
                                    visited[ny][nx] = true;
                                }
                            }
                        }
                    }
                    list.add(qSize);
                }
            }
        }

        Collections.sort(list);

        System.out.println(list.size());
        for (int i : list) {
            System.out.println(i);
        }

    }


}