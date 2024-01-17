import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] connect;
    static boolean[] visited;
    static int leaf = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        connect = new List[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            connect[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            connect[U].add(V);
            connect[V].add(U);
        }

        DFS(1);

        System.out.println((double)W / leaf);
    }

    static void DFS(int x){
        visited[x] = true;
        boolean isLeaf = true;
        for (int i = 0; i < connect[x].size(); i++) {
            int next = connect[x].get(i);
            if(!visited[next]){
                isLeaf = false;
                DFS(next);
            }
        }
        if(isLeaf)
            leaf++;
    }

}