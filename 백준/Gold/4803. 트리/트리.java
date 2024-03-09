import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {


    static int[] parents;

    public static void init() {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public static int findRoot(int n) {
        if (parents[n] == n)
            return n;
        return findRoot(parents[n]);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = 1;
        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0)
                return;
            parents = new int[n + 1];
            int[] connected = new int[n + 1];
            init();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                a = findRoot(a);
                b = findRoot(b);
                if (a > b)
                    parents[a] = b;
                else
                    parents[b] = a;
                connected[a]++;
                connected[b]++;

            }

            TreeMap<Integer, Integer> roots = new TreeMap<>();
            int[] edges = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int root = findRoot(i);
                edges[root] += connected[i];
                if (roots.containsKey(root))
                    roots.put(root, roots.get(root) + 1);
                else
                    roots.put(root, 1);
            }
            int treeCnt = 0;
            for (Map.Entry<Integer, Integer> entry : roots.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if (value >= 1) {
                    if (edges[key] / 2 < value)
                        treeCnt++;
                }
            }

            if (treeCnt == 0) {
                System.out.println("Case " + TC + ": No trees.");
            } else if (treeCnt == 1) {
                System.out.println("Case " + TC + ": There is one tree.");
            } else {
                System.out.println("Case " + TC + ": A forest of " + treeCnt + " trees.");
            }

            TC++;

        }

    }

}