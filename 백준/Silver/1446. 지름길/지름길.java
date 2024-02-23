import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int K;

    public static class Shortcut {

        int start, end, distance;

        public Shortcut(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        List<Shortcut> shortcuts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            if (end <= K && distance < end - start)
                shortcuts.add(new Shortcut(start, end, distance));
        }

        Collections.sort(shortcuts, new Comparator<Shortcut>() {
            @Override
            public int compare(Shortcut o1, Shortcut o2) {
                if (o1.end == o2.end) {
                    if (o1.start == o2.start)
                        return o1.distance - o2.distance;
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });

        DFS(0, 0, shortcuts);
        System.out.println(answer);

    }

    public static void DFS(int now, int cost, List<Shortcut> shortcutList) {
        if (shortcutList.isEmpty()) {
            answer = Math.min(answer, cost + K - now);
            return;
        }
        Shortcut shortcut = shortcutList.get(0);
        List<Shortcut> shortcutCopy = new ArrayList<>(shortcutList);
        shortcutCopy.remove(0);
        DFS(now, cost, shortcutCopy);
        if (shortcut.start >= now) {
            DFS(shortcut.end, cost + shortcut.start - now + shortcut.distance, shortcutCopy);
        }
    }

}