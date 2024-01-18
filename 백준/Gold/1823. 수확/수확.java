import java.awt.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr= new int[N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(func(0, N-1, 1));

    }

    public static int func(int left, int right, int cnt){
        if(left > right) return 0;
        if(dp[left][right] != 0) return dp[left][right];
        return dp[left][right] = Math.max(func(left+1, right, cnt+1) + arr[left] * cnt, func(left, right-1, cnt+1) + arr[right] * cnt);
    }
}