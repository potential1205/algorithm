package streak.d20250221.b2420_사파리월드;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextInt();
        long m = sc.nextInt();
        long result = n-m;

        System.out.println(Math.abs(result));
    }
}