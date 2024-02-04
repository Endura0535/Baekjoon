import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[][] board = new String[n][m];


        int Q_num = sc.nextInt();
        Queue<Pos> q = new LinkedList<Pos>();
        for (int i = 0; i < Q_num; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            board[x][y] = "Q";
            q.add(new Pos(x, y, "Q"));
        }
        int K_num = sc.nextInt();
        for (int i = 0; i < K_num; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            board[x][y] = "K";
            q.add(new Pos(x, y, "K"));
        }
        int P_num = sc.nextInt();
        for (int i = 0; i < P_num; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            board[x][y] = "P";
            q.add(new Pos(x, y, "P"));
        }
        while (!q.isEmpty()) {
            Pos now = q.poll();
            if (now.type == "Q") {
                QueenMove(now, board, n, m);
            } else if (now.type == "K") {
                KnightMove(now, board, n, m);
            }
        }

        System.out.println(n * m - Q_num - K_num - P_num - cnt);

    }

    public static void KnightMove(Pos pos, String[][] board, int n, int m) {
        int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};
        for (int d = 0; d < 8; d++) {
            int nx = pos.x + dx[d];
            int ny = pos.y + dy[d];
            if (0 > nx || nx >= n || 0 > ny || ny > m) continue;
            if (board[nx][ny] == null) {
                board[nx][ny] = "V";
                cnt++;
            }
        }
    }

    public static void QueenMove(Pos pos, String[][] board, int n, int m) {
        int nx;
        int ny;
        int[] dx = {1, 1, -1, -1, 1, 0, -1, 0};
        int[] dy = {1, -1, -1, 1, 0, 1, 0, -1};

        for (int d = 0; d < 8; d++) {
            nx = pos.x + dx[d];
            ny = pos.y + dy[d];
            while (true) {
                if (0 > nx || nx >= n || 0 > ny || ny >= m) break;
                if (board[nx][ny] == null) {
                    board[nx][ny] = "V";
                    nx += dx[d];
                    ny += dy[d];
                    cnt++;
                } else if (board[nx][ny] == "V") {
                    nx += dx[d];
                    ny += dy[d];
                } else {
                    break;
                }
            }
        }
    }


    public static class Pos {
        int x;
        int y;
        String type;

        public Pos(int x, int y, String type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

}