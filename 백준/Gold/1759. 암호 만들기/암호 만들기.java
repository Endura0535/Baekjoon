import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static char[] cArr, newArr;
	static int L, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		cArr = new char[C];
		newArr = new char[L];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			cArr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(cArr);

		perm(0, 0, 0, 0);

	}

	public static void perm(int start, int count, int countCon, int countVowel) {
		
		if (count == L) {
			if (countCon >= 2 && countVowel >= 1) {
				for (int i = 0; i < L; i++) {
					System.out.print(newArr[i]);
				}
				System.out.println();
			}
			return;
		}
		for (int i = start; i < C; i++) {
			newArr[count] = cArr[i];
			if (cArr[i] == 'a' || cArr[i] == 'e' || cArr[i] == 'i' || cArr[i] == 'o' || cArr[i] == 'u')
				perm(i + 1, count + 1, countCon, countVowel+1);
			else {
				perm(i + 1, count + 1, countCon+1, countVowel);
			}
		}
	}

}