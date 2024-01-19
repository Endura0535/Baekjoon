import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N + 1][10];
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                int x = 0;
                for (int k = 0; k <= j; k++) {
                    x += dp[i - 1][k];
                }
                x %= 10007;
                dp[i][j] = x;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[N][i];
        }
        System.out.println(answer % 10007);
    }
}