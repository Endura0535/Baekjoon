import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();
        int[] block = new int[W];
        int answer = H * W;
        int wallCnt = 0;
        int maxH = 0;
        for (int i = 0; i < W; i++) {
            int x = sc.nextInt();
            maxH = Math.max(maxH, x);
            block[i] = x;
            answer -= x;
            if (x != 0)
                wallCnt++;
        }
        if (wallCnt <= 1) {
            System.out.println(0);
            return;
        }

        int left = 0;
        for (int i = 0; i < W; i++) {
            if (block[i] > left) {
                left = block[i];
            }
            answer -= H - left;
        }

        int right = 0;
        for (int i = W - 1; i >= 0; i--) {
            if (block[i] > right) {
                right = block[i];
            }
            answer -= H - right;
        }
        answer += (H - maxH) * W;
        System.out.println(answer);
    }

}