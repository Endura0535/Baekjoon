import java.awt.*;
import java.util.*;

public class Main {

    static int answer = -1;
    static ArrayList<Integer> coinList;
    static int n, k;
    static boolean visited[];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        visited = new boolean[k + 1];
        TreeSet<Integer> coins = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            coins.add(sc.nextInt());
        }
        coinList = new ArrayList<>(coins);
        Collections.sort(coinList, Collections.reverseOrder());

        BFS();
        System.out.println(answer);

    }

    public static void BFS() {

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(k, 0));
        visited[k] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == 0) {
                answer = p.y;
                return;
            }
            for (int c : coinList) {
                if (p.x - c >= 0) {
                    if (!visited[p.x - c]) {
                        queue.add(new Point(p.x - c, p.y + 1));
                        visited[p.x - c] = true;
                    }
                }
            }
        }

    }

}