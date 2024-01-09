import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int[]> schedule = new ArrayList<>();

        for (int day = 1; day <= N; day++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int[] today = {day, day + T - 1, P};
            schedule.add(today);
        }

        Collections.sort(schedule, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    if (o1[2] == o2[2]) {
                        return o2[0] - o1[0];
                    }
                    return o1[2] - o2[2];
                }
                return o1[1] - o2[1];
            }
        });

        int[] pay = new int[N + 1];
        Arrays.fill(pay, 0);
        final int[] last = {0,0};

        schedule.forEach(s -> {
            if (s[1] <= N) {
                for (int i = last[0] + 1; i < s[1]; i++) {
                    if (i > N)
                        break;
                    pay[i] = pay[last[0]];
                }
                last[0] = s[1];
                pay[s[1]] = Math.max(pay[s[1]], Math.max(pay[s[0] - 1] + s[2], pay[s[1] - 1]));
                last[1] = pay[s[1]];
            }
        });

        System.out.println(last[1]);

    }
}