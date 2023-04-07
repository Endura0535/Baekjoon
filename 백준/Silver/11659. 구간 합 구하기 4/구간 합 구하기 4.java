import java.util.Scanner;

public class Main{

	static int N, M, i, j;
	static int[] arr;
	static int[] answer;
	static int[] sum;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		answer = new int[M];
		sum = new int[N];
		
		for (int k = 0; k < N; k++) {
			arr[k] = sc.nextInt();
			if (k == 0) {
				sum[k] = arr[0];
			} else {
				sum[k] = sum[k - 1] + arr[k];
			}
		}
		for (int k = 0; k < M; k++) {
			i = sc.nextInt();
			j = sc.nextInt();
			if(i==1) System.out.println(sum[j-1]);
			else	System.out.println(sum[j-1]-sum[i-2]);
		}

	}

}
