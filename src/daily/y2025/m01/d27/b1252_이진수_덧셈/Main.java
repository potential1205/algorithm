package daily.y2025.m01.d27.b1252_이진수_덧셈;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();

        BigInteger num1 = new BigInteger(str1, 2);
        BigInteger num2 = new BigInteger(str2, 2);

       System.out.println(num1.add(num2).toString(2));
    }
}
