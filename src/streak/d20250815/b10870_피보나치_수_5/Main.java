package streak.d20250815.b10870_피보나치_수_5;

import java.util.Scanner;

public class Main {

    static int recursive(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(recursive(n));
    }
}
