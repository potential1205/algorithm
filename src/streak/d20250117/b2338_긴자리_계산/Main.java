package streak.d20250117.b2338_긴자리_계산;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        BigInteger n = sc.nextBigInteger();
        BigInteger m = sc.nextBigInteger();

        StringBuilder sb = new StringBuilder();
        sb.append(n.add(m)).append("\n").append(n.subtract(m)).append("\n").append(n.multiply(m)).append("\n");

        System.out.println(sb);
    }
}
