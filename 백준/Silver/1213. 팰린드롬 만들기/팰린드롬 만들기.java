import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 이름 입력
		char[] name = sc.next().toCharArray();
		// 알파벳 별 개수 입력
		int[] alphaCount = new int[26];
		for (char n : name) {
			alphaCount[n - 'A']++;
		}
		int oddCount = 0;
		int oddNumber = 0;

		for (int i = 0; i < 26; i++) {
			if (alphaCount[i] % 2 == 1) {
				oddCount++;
				oddNumber = i;
			}
		}

		// 입력의 길이가 홀수이고 홀수 개수인 알파벳이 1개가 아니면 불가능 출력
		if (name.length % 2 == 1 && oddCount != 1)
			System.out.println("I'm Sorry Hansoo");
		// 입력의 길이가 짝수이고 홀수 개수인 알파벳이 0개가 아니면 불가능 출력
		else if (name.length % 2 == 0 && oddCount != 0)
			System.out.println("I'm Sorry Hansoo");
		else { // 불가능이 아니면 팰린드롬 출력
			String answer = "";
			for (int i = 0; i < 26; i++) {
				if (alphaCount[i] != 0) {
					for (int j = 0; j < alphaCount[i] / 2; j++) {
						answer += String.valueOf((char) (i + 65));
					}
				}
			}
			// 홀수 있으면 가운데 처리
			if (oddCount != 0)
				answer += String.valueOf((char) (oddNumber + 65));

			for (int i = 25; i >= 0; i--) {
				if (alphaCount[i] != 0 && alphaCount[i] != 1) {
					for (int j = 0; j < alphaCount[i] / 2; j++) {
						answer += String.valueOf((char) (i + 65));
					}
				}
			}
			System.out.println(answer);
		}

	}

}