package daily.y2025.m02.d12.b1464_뒤집기_3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        String answer = String.valueOf(str.charAt(0));

        for (int i = 1; i < str.length(); i++) {
            if (answer.charAt(i-1) < str.charAt(i)) {
                answer = str.charAt(i) + answer;
            } else {
                answer = answer + str.charAt(i);
            }
        }

        for (int i = 0; i < answer.length(); i++) {
            System.out.print(answer.charAt(answer.length()-1-i));
        }
    }
}
