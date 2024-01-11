import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int now = Integer.parseInt(st.nextToken());
        List<Integer> gap = new ArrayList<>();
        int answer = -now;
        for (int i = 1; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            gap.add(h - now);
            now = h;
        }
        answer += now;
        Collections.sort(gap, Collections.reverseOrder());

        int minus = 0;
        for (int i = 0; i < K - 1; i++) {
            answer -= gap.get(i);
        }

        System.out.println(answer);

    }
}