import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] map = new int[100001];
        Arrays.fill(map, Integer.MAX_VALUE);
        if (K < N) {
            System.out.println(N - K);
        } else if (N == K) {
            System.out.println(0);
        } else {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(N, 0));
            map[N] = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Point point = queue.poll();

                    if (point.x < K) {
                        if (point.x * 2 <= 100000) {
                            if (map[point.x * 2] > point.y) {
                                queue.add(new Point(point.x * 2, point.y));
                                map[point.x * 2] = point.y;
                            }
                        }
                        if (point.x + 1 <= 100000) {
                            if (map[point.x + 1] > point.y + 1) {
                                queue.add(new Point(point.x + 1, point.y + 1));
                                map[point.x + 1] = point.y + 1;
                            }
                        }
                    } else if (point.x == K) {
                        continue;
                    }

                    if (point.x >= 1) {
                        if (map[point.x - 1] > point.y + 1) {
                            queue.add(new Point(point.x - 1, point.y + 1));
                            map[point.x - 1] = point.y + 1;
                        }
                    }

                }
            }
            System.out.println(map[K]);
        }

    }

}