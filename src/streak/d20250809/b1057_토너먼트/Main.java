package streak.d20250809.b1057_토너먼트;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int count = 0;

		while(num1 != num2) {
			num1 = num1 / 2 + num1 % 2;
			num2 = num2 / 2 + num2 % 2;
			count++;
		}
		System.out.println(count);

	}
}
