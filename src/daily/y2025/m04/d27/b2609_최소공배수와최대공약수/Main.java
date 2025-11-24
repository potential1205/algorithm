package daily.y2025.m04.d27.b2609_최소공배수와최대공약수;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());

        int maxNum = gcd(num1, num2);

        System.out.println(maxNum);
        System.out.println(num1 * num2 / maxNum);
    }

    public static int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }

        return gcd(num2, num1 % num2);
    }
}