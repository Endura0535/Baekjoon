import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		ArrayList<Point> arr = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int start, finish;
			
			start = Integer.parseInt(st.nextToken());
			finish = Integer.parseInt(st.nextToken());
			
			arr.add(new Point(start,0));
			arr.add(new Point(finish,1));
			
		}
				
		Collections.sort(arr, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.x == o2.x)
					return o2.y - o1.y;
				return o1.x- o2.x;
			}
		});
				
		int stk = 0, maxStk = 0;
		for (Point a:arr) {
			if(a.y == 1) {
				stk--;
			}else {
				stk++;
			}
			maxStk = Math.max(maxStk, stk);
		}
		
		System.out.println(maxStk);
	}

}