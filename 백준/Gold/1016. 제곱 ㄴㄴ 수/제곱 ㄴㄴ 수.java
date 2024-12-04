import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long min, max;
    static boolean[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        arr = new boolean[(int) (max - min + 1)];
        int answer = (int) (max - min + 1);
        for (long i = 2; i <= Math.sqrt(max); i++) {
            for (long j = min % (i * i) == 0 ? min / (i * i) : min / (i * i) + 1; j <= max / (i * i); j++) {
                if ((int) (i * i * j - min) >= max - min + 1)
                    continue;
                if (!arr[(int) (i * i * j - min)]) {
                    arr[(int) (i * i * j - min)] = true;
                    answer--;
                }
            }
        }
        System.out.println(answer);
    }
}