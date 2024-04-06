import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Edge>[] graph;
    static int V, E, start;
    static int[] answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        start = Integer.parseInt(br.readLine());

        graph = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        answer = new int[V + 1];
        Arrays.fill(answer, Integer.MAX_VALUE);
        answer[start] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e, d));
        }

        dijkstra(start);

        for (int i = 1; i <= V; i++) {
            int ans = answer[i];
            if (ans == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(ans);
        }

    }

    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (answer[current.to] < current.weight) continue;
            for (Edge next : graph[current.to]) {
                int nextCost = current.weight + next.weight;
                if (nextCost < answer[next.to]) {
                    answer[next.to] = nextCost;
                    pq.offer(new Edge(next.to, nextCost));
                }
            }
        }
    }
}