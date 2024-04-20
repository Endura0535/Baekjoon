import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;

    public static int findParent(int n) {
        if (n == parents[n])
            return n;
        return parents[n] = findParent(parents[n]);
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b)
            parents[b] = a;
        else
            parents[a] = b;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (operation == 0)
                union(a, b);
            else {
                if (findParent(a) == findParent(b))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }

    }

}