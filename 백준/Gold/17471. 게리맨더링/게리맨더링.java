import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 각 구역 인구 차이의 최소값
	static int answer = Integer.MAX_VALUE;
	// 구역 개수
	static int N;
	// 모든 구역의 인구 수
	static int total_population = 0;
	// 각 구역의 인구 수
	static int[] population;
	// 구역의 연결 정보
	static boolean[][] map;
	// 방문 체크
	static boolean[] isVisited;
	static boolean[] isSelected;


	static int[] subsets;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		population = new int[N + 1];
		total_population = 0;
		map = new boolean[N + 1][N + 1];
		isVisited = new boolean[N + 1];
		isSelected = new boolean[N + 1];
		subsets = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int people = Integer.parseInt(st.nextToken());
			population[i] = people;
			total_population += people;
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			for (int j = 0; j < count; j++) {
				int to = Integer.parseInt(st.nextToken());
				map[i][to] = true;
				map[to][i] = true;
			}
		}
		
		subset(0);
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		System.out.println(answer);

	}

	// 부분집합
	public static void subset(int depth) {
		if (depth == N) {
			// 이 집합과 나머지들의 집합이 가능한지 확인 후 가능할 경우 답에 업데이트 후 종료
			if(diff(isSelected) != -1)
				answer = Math.min(answer, diff(isSelected));
			return;
		}
		isSelected[depth] = true;
		subset(depth + 1);
		isSelected[depth] = false;
		subset(depth + 1);
	}
	
	public static int diff(boolean[] isSelected) {
		
		int sum1 = 0;
		
		// 방문해야하는 구역 목록
		ArrayList<Integer> arr1 = new ArrayList<>();
		ArrayList<Integer> arr2 = new ArrayList<>();

		// 방문한 구역 목록
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();

		// 방문한 배열 넣기
		for (int i = 1; i <= N; i++) {
			if(isSelected[i])
				arr1.add(i);
			else
				arr2.add(i);
		}
		
		// arr1이 공집합이 아닐 경우 가능한 집합인지 확인
		if(!arr1.isEmpty()) {
			q1.add(arr1.remove(0));
			
			while(!q1.isEmpty()) {
				
				int n1 = q1.poll();
				sum1 += population[n1];
				
				for (int i = 1; i <= N; i++) {
					if(arr1.indexOf(i) != -1){
						if(!isVisited[i] && map[n1][i]) {
							q1.add(i);
							arr1.remove(arr1.indexOf(i));
						}
					}
				}
			}
		}
		
		if(!arr2.isEmpty()) {
			q2.add(arr2.remove(0));
			while(!q2.isEmpty()) {
				int n2 = q2.poll();				
				for (int i = 1; i <= N; i++) {
					if(arr2.indexOf(i) != -1){
						if(!isVisited[i] && map[n2][i]) {
							q2.add(i);
							arr2.remove(arr2.indexOf(i));
						}
					}
				}
			}
		}

		
		int sum2 = total_population-sum1;
		
		if(arr1.isEmpty() && arr2.isEmpty())
			return Math.abs(sum1-sum2);
		else
			return -1;
	}

}