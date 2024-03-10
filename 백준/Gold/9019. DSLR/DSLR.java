import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int num;
    static int result;
    static boolean[] visited;
    static String[] ans;

    static class Info {
        int num;
        String str;

        public Info(int num, String str) {
            this.num = num;
            this.str = str;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = new String[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            result = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            ans[i] = BFS();
        }

        for (String s: ans) {
            System.out.println(s);
        }
    }

    public static String BFS() {
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(num, ""));

        while (!queue.isEmpty()) {
            Info info = queue.poll();

            if (visited[info.num]) 
                continue;
            
            visited[info.num] = true;

            if (info.num == result) 
                return info.str;

            queue.offer(new Info((info.num * 2) % 10000, info.str + "D"));
            
            int numS = (info.num == 0)? 9999: info.num - 1;
            queue.offer(new Info(numS, info.str + "S"));

            int numL = (info.num / 1000) + ((info.num / 100) % 10) * 1000 + ((info.num / 10) % 10) * 100 + (info.num % 10) * 10;
            queue.offer(new Info(numL, info.str + "L"));

            int numR = (info.num / 1000) * 100 + ((info.num / 100) % 10) * 10 + (info.num / 10) % 10 + (info.num % 10) * 1000;
            queue.offer(new Info(numR, info.str + "R"));
        }

        return "";
    }
}