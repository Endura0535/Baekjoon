import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] bonus;
    static int N;
    static long answer = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bonus = new int[N][N];
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                bonus[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> aGroup = new ArrayList<>();
        aGroup.add(0);
        List<Integer> bGroup = new ArrayList<>();
        for (int b = 1; b < N; b++) {
            dfs(1, aGroup, bGroup, b, 0, 0);
        }

        System.out.println(answer);
    }

    public static void dfs(int idx, List<Integer> aGroup, List<Integer> bGroup, int b, int aSum, int bSum) {

        if (idx == N) {
            answer = Math.min(answer, Math.abs(aSum - bSum));
            return;
        }

        if (idx != b) {
            int newAsum = aSum;
            for (int n : aGroup) {
                newAsum += bonus[n][idx];
                newAsum += bonus[idx][n];
            }
            aGroup.add(idx);
            dfs(idx + 1, aGroup, bGroup, b, newAsum, bSum);

            aGroup.remove(aGroup.size() - 1);
        }
        int newBsum = bSum;
        for (int n : bGroup) {
            newBsum += bonus[n][idx];
            newBsum += bonus[idx][n];
        }
        bGroup.add(idx);
        dfs(idx + 1, aGroup, bGroup, b, aSum, newBsum);
        bGroup.remove(bGroup.size() - 1);
    }
}
