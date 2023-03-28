import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
				
		System.out.println(sugar(N, N/5));

	}
	
	public static int sugar(int amount, int count5) {
		if(count5 == -1)
			return -1;
		if((amount - count5*5)%3 == 0)
			return count5 + (amount - count5*5)/3;
		return sugar(amount, count5-1);
	}

}