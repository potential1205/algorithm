package streak.d20250203.b2145_숫자_놀이;

import java.util.Scanner;

public class Main {

    static boolean isOneDigit(int value) {
        int cnt = 0;

        while (value > 0) {
            cnt++;
            value /= 10;
        }

        return cnt == 1;
    }

    static void process(int value) {
        while (true) {
            if (isOneDigit(value)) {
                System.out.println(value);
                break;
            }

            int temp = value;
            int cum = 0;
            while (temp > 0) {
                cum += (temp%10);
                temp/=10;
            }

            value = cum;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();

            if (n == 0) {
                break;
            }

            process(n);
        }
    }
}
