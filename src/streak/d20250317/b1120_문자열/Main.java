package streak.d20250317.b1120_문자열;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < str2.length() - str1.length() + 1; i++) {
            int cnt = 0;

            for (int j = 0; j < str1.length(); j++) {
                if (str1.charAt(j) != str2.charAt(i+j)) {
                    cnt++;
                }
            }

            answer = Math.min(answer, cnt);
        }

        System.out.println(answer);
    }
}
