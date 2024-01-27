import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static int[][] board;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		
		boolean[]isUsed = new boolean[26];
		String s;
		for (int i = 0; i < R; i++) {
			s = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = s.charAt(j) - '0' - 17;
			}
		}
		
		move(0,0,1, isUsed);
		
		System.out.print(answer);
		
		
	}
	
	public static void move(int x, int y, int count, boolean[] isUsed) {
		
		//알파벳 사용 체크
		isUsed[board[x][y]] = true;
		
		//상
		if(canGo(x, y, 'U', isUsed)) {
			move(x-1, y, count+1, isUsed);
			isUsed[board[x-1][y]] = false;
		}
		//하
		if(canGo(x, y, 'D', isUsed)) {
			move(x+1, y, count+1, isUsed);
			isUsed[board[x+1][y]] = false;
		}
		//좌
		if(canGo(x, y, 'L', isUsed)) {
			move(x, y-1, count+1, isUsed);
			isUsed[board[x][y-1]] = false;
		}
		//우
		if(canGo(x, y, 'R', isUsed)) {
			move(x, y+1, count+1, isUsed);
			isUsed[board[x][y+1]] = false;
		}
		
		answer = Math.max(answer, count);
	}
	
	
	/**
	 * 
	 * @param x		// 현재 x 좌표
	 * @param y		// 현재 y 좌표
	 * @param c		// 가고 싶은 방향
	 * @return		// 갈 수 있는지 여부
	 */
	public static boolean canGo(int x, int y, char c, boolean[] isUsed) {
		int dx = x, dy = y;
		switch (c) {
		case 'L':
			if(y == 0)
				return false;
			dy--;
			break;
		case 'R':
			if(y == C-1)
				return false;
			dy++;
			break;
		case 'U':
			dx--;
			if(x==0)
				return false;
			break;
		case 'D':
			if(x == R-1)
				return false;
			dx++;
			break;
		}
		
		//알파벳이 중복되지 않을 경우 갈 수 있음
		if(isUsed[board[dx][dy]] == true) {
			return false;
		}
		
		return true;
	}
	
	

}