import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int[][] upDp = new int[N][M];
        int sum = map[N - 1][0];
        upDp[N - 1][0] = sum;
        for (int i = N - 2; i >= 0; i--) {
            sum += map[i][0];
            upDp[i][0] = sum;
        }
        sum = map[N - 1][0];
        for (int i = 1; i < M; i++) {
            sum += map[N - 1][i];
            upDp[N - 1][i] = sum;
        }

        for (int y = N - 2; y >= 0; y--) {
            for (int x = 1; x < M; x++) {
                upDp[y][x] = Math.max(upDp[y + 1][x], upDp[y][x - 1]) + map[y][x];
            }
        }

        int[][] downDP = new int[N][M];
        sum = map[N - 1][M - 1];
        downDP[N - 1][M - 1] = sum;
        for (int i = M - 2; i >= 0; i--) {
            sum += map[N - 1][i];
            downDP[N - 1][i] = sum;
        }
        sum = map[N - 1][M - 1];
        for (int i = N - 2; i >= 0; i--) {
            sum += map[i][M - 1];
            downDP[i][M - 1] = sum;
        }
        for (int y = N - 2; y >= 0; y--) {
            for (int x = M - 2; x >= 0; x--) {
                downDP[y][x] = Math.max(downDP[y + 1][x], downDP[y][x + 1]) + map[y][x];
            }
        }

        int answer = Integer.MIN_VALUE;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                answer = Math.max(answer, upDp[y][x] + downDP[y][x]);
            }
        }

        System.out.println(answer);

    }

}