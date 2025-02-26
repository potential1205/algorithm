package streak.d20250226.b1964_오각형;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long answer = 5;
        long temp = 7;

        for (int i = 1; i < n; i++) {
            answer = answer + temp;
            temp = temp + 3;
        }

        System.out.println(answer%45678);
    }
}