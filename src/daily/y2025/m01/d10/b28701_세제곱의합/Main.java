package daily.y2025.m01.d10.b28701_세제곱의합;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long answer = 0;

        for (int i=1; i<=n; i++) {
            answer += i;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append(" ");

        answer = (long) Math.pow(answer, 2);

        sb.append(answer).append(" ");
        sb.append(answer).append(" ");
        System.out.println(sb);
    }
}
