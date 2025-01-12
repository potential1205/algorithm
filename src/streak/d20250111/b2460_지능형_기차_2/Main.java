package streak.d20250111.b2460_지능형_기차_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;
        int num = 0;

        for (int i=0; i<10; i++) {
            int out = sc.nextInt();
            int in = sc.nextInt();
            num = num - out + in;
            answer = Math.max(answer, num);
        }

        System.out.println(answer);
    }
}
