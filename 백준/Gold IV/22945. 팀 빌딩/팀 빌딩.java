import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] line = new int[N];
        for (int i = 0; i < N; i++) {
            line[i] = sc.nextInt();
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = Math.max(i, N - i - 1); j > 0; j--) {
                if (i + j < N) {
                    if (line[i] <= line[i + j]) {
                        answer = Math.max(answer, line[i] * (j - 1));
                        break;
                    }
                }
                if (0 <= i - j) {
                    if (line[i] <= line[i - j]) {
                        answer = Math.max(answer, line[i] * (j - 1));
                        break;
                    }
                }
            }
        }

        System.out.println(answer);

    }

}