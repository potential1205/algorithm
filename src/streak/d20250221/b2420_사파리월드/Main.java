package streak.d20250221.b2420_사파리월드;

import java.math.BigInteger;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //long n = sc.nextInt();
        //long m = sc.nextInt();
        //long result = n-m;

        //System.out.println(Math.abs(result));

        BigInteger val1 = new BigInteger(String.valueOf(sc.nextLong()));
        BigInteger val2 = new BigInteger(String.valueOf(sc.nextLong()));

        System.out.println(val1.subtract(val2).abs());


    }
}