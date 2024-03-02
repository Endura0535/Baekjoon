import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int[][] squareMap = new int[n][m];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (map[i][0] == 1)
                answer = 1;
            squareMap[i][0] = map[i][0];
        }
        for (int i = 0; i < m; i++) {
            if (map[0][i] == 1)
                answer = 1;
            squareMap[0][i] = map[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (map[i][j] == 1) {

                    int pre = squareMap[i - 1][j - 1];
                    int max = 0;

                    for (int k = 1; k <= pre; k++) {
                        if (map[i - k][j] == 0 || map[i][j - k] == 0) {
                            break;
                        }
                        max = k;
                    }

                    squareMap[i][j] = max + 1;
                    answer = Math.max(answer, max + 1);

                }
            }
        }

        System.out.println(answer * answer);

    }

}