package streak.d20250404.b1094_막대기;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int answer = 0;

        while (n > 0) {
            if (n % 2 == 1) {
                answer++;
            }

            n = n / 2;
        }

        System.out.println(answer);
    }
}
