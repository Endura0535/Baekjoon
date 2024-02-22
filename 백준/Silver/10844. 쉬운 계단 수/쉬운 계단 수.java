import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(9);
            return;
        }

        int[][] dp = new int[N + 1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        int answer = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j - 1 >= 0)
                    dp[i][j] += dp[i - 1][j - 1];
                if (j + 1 < 10)
                    dp[i][j] += dp[i - 1][j + 1];
                dp[i][j] %= 1000000000;
                if (i == N){
                    answer += dp[i][j];
                    answer %=1000000000;
                }
            }
        }

        System.out.println(answer);

    }

}