import java.util.*;
import java.awt.*;

class Solution {
    
    // 사방탐색
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    public static int solution(int[][] maps) {      // BFS
        int answer = 1;     // 정답
        
        int xLen = maps[0].length;
        int yLen = maps.length;
        boolean visited[][] = new boolean[yLen][xLen];    // 방문 체크
        visited[0][0] = true;
        Point pos = new Point(0, 0);    // 현재 위치
        Queue<Point> q = new LinkedList<>();
        q.add(pos);
        while (!q.isEmpty()) { 
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Point now = q.poll();
                int cx = now.x;
                int cy = now.y;
                visited[cy][cx] = true;

                if (cx == xLen-1 && cy == yLen-1)
                    return answer;
                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];
                    if (canGo(maps, nx, ny))
                        if (!visited[ny][nx]) {
                            q.add(new Point(nx, ny));
                            visited[ny][nx] = true;
                        }
                }
            }
            answer++;
        }
        return -1;
    }
    
    public static boolean canGo(int[][] maps, int x, int y) {
        if (x < 0 || y < 0 || x >= maps[0].length || y >= maps.length)
            return false;
        else if (maps[y][x] == 0)
            return false;
        return true;
    }
}