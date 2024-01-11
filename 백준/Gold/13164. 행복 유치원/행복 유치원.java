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
        int[] height = new int[N];
        st = new StringTokenizer(br.readLine());
        height[0] = Integer.parseInt(st.nextToken());
        int now = height[0];
        List<Integer> gap = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            gap.add(height[i] - now);
            now = height[i];
        }

        Collections.sort(gap, Collections.reverseOrder());

        int minus = 0;
        for (int i = 0; i < K - 1; i++) {
            minus += gap.get(i);
        }

        System.out.println(height[N - 1] - height[0] - minus);

    }
}