import java.awt.Point;
import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N;
	static char[][] picB;
	static char[][] picNB;
	static boolean[][] isVisited;
	static int notBlindArea = 0, blindArea = 0;

	// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isVisited = new boolean[N][N];
		picNB = new char[N][N];
		picB = new char[N][N];

		for (int i = 0; i < N; i++) {
			picNB[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(picNB[i][j] == 'R')
					picB[i][j] = 'G';
				else
					picB[i][j] = picNB[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!isVisited[i][j]) {
					findNB(j, i);
				}
			}
		}
		
		isVisited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!isVisited[i][j]) {
					findB(j, i);
				}
			}
		}
		
		

		System.out.println(notBlindArea + " " + blindArea);

	}

	public static void findNB(int x, int y) {
		int nx, ny;
		char c;

		Queue<Point> q = new LinkedList<>();

		q.add(new Point(x, y));
		isVisited[y][x] = true;
		c = picNB[y][x];
		Point p;
		while (!q.isEmpty()) {
			int s = q.size();
			for (int j = 0; j < s; j++) {
				p = q.poll();
				for (int i = 0; i < 4; i++) {
					nx = p.x + dx[i];
					ny = p.y + dy[i];
					if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
						if (isVisited[ny][nx] == false & picNB[ny][nx] == c) {
							q.add(new Point(nx, ny));
							isVisited[ny][nx] = true;
						}
					}
				}
			}
		}
			notBlindArea++;
	}
	
	public static void findB(int x, int y) {
		int nx, ny;
		char c;

		Queue<Point> q = new LinkedList<>();

		q.add(new Point(x, y));
		isVisited[y][x] = true;
		c = picB[y][x];
		Point p;
		while (!q.isEmpty()) {
			int s = q.size();
			for (int j = 0; j < s; j++) {
				p = q.poll();
				for (int i = 0; i < 4; i++) {
					nx = p.x + dx[i];
					ny = p.y + dy[i];
					if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
						if (isVisited[ny][nx] == false & picB[ny][nx] == c) {
							q.add(new Point(nx, ny));
							isVisited[ny][nx] = true;
						}
					}
				}
			}
		}
			blindArea++;
	}

}