import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<String> S = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			S.add(br.readLine());
		}

		Collections.sort(S);

		String s;
		int answer = 0;

		for (int i = 0; i < M; i++) {
			s = br.readLine();
			if (S.indexOf(s) != -1)
				answer++;
		}
		
		System.out.println(answer);
	}

}