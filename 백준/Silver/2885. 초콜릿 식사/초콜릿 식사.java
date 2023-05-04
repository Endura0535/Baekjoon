import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int size, n, cnt = 0;
		n = mod(K);

		if (n == 0) {
			System.out.println(K + " " + 0);
			return;
		}
		size = (int) Math.pow(2, (int)(Math.log(K)/Math.log(2)) + 1);

		int nowSize = size;

		while (K != 0) {
			cnt++;
			if (K >= nowSize / 2)
				K -= nowSize / 2;
			nowSize /= 2;
		}
		System.out.println(size + " " + cnt);
	}

	public static int mod(int k) {
		if (k == 1)
			return 0;
		if (k % 2 == 0)
			return mod(k / 2);
		return k;
	}

}