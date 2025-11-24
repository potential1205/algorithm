package daily.y2025.m04.d30.b1110_더하기_사이클;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int value = n;
        int cnt = 0;

        while (true) {
            int a = value / 10;
            int b = value % 10;

            value = b * 10 + (a + b) % 10;
            cnt++;

            if (n == value) {
                break;
            }
        }

        System.out.println(cnt);
    }
}
