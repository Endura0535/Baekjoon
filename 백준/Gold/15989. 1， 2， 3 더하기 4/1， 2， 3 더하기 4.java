import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[][] dp = new int[10001][4];

        for (int n = 0; n <= 10000; n++) {
            dp[n][0] = 0;
            dp[n][1] = 1;
            dp[n][2] = n / 2 + 1;
        }
        dp[1][2] = 1;
        dp[1][3] = 1;
        dp[2][2] = 1;
        dp[2][3] = 2;
        dp[3][2] = 2;
        dp[3][3] = 3;
        dp[4][2] = 3;
        dp[4][3] = 4;

        for (int i = 5; i <= 10000; i++) {
            dp[i][3] = 2 + (i - 2) / 2 + dp[i - 3][3];
        }


        int n;
        for (int i = 0; i < T; i++) {
            n = sc.nextInt();
            System.out.println(dp[n][3]);
        }

    }

}