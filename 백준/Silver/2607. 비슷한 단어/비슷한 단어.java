import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 단어 개수
		Map<Character, Integer> firstMap = new HashMap<>();
		Map<Character, Integer> newMap;
		int answer = 0;
		// 첫번째 단어 받기
		char[] word = br.readLine().toCharArray();
		for (char c : word) {
			firstMap.put(c, firstMap.getOrDefault(c, 0) + 1);
		}
		// 비교할 단어 받기
		for (int i = 1; i < N; i++) {
			newMap = new HashMap<>();
			char[] newWord = br.readLine().toCharArray();
			for (char c : newWord) {
				newMap.put(c, newMap.getOrDefault(c, 0) + 1);
			}
			int notSame = 0;
			boolean check = true;
			int difCnt = 0;
			Map<Character, Integer> differ = new HashMap<>();
			for (char mapKey : firstMap.keySet()) {
				int firstVal = firstMap.get(mapKey);
				int newVal = newMap.getOrDefault(mapKey, 0);
				if (firstVal != newVal) {
					notSame += Math.abs(firstVal - newVal);
					if (notSame > 2)
						break;
					differ.put(mapKey, firstVal - newVal);
				}
			}
			for (char mapKey : newMap.keySet()) {
				int firstVal = firstMap.getOrDefault(mapKey, 0);
				int newVal = newMap.get(mapKey);
				if (firstVal != newVal) {
					if (firstVal == 0)
						notSame += Math.abs(firstVal - newVal);
					if (notSame > 2)
						break;
					differ.put(mapKey, firstVal - newVal);
				}
			}
			if (notSame > 2)
				continue;
			int dif = 0;
			for (char c : differ.keySet()) {
				if (Math.abs(differ.get(c)) > 1) {
					check = false;
					break;
				}
				dif += differ.get(c);
			}
			if (Math.abs(dif) > 1)
				check = false;
			if (check)
				answer++;

		}

		System.out.println(answer);
	}

}