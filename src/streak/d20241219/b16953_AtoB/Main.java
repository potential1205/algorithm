package streak.d20241219.b16953_AtoB;

import java.util.Scanner;

public class Main {

    static long answer = Long.MAX_VALUE;

    static void solution(long value, long source, int depth) {
        if (value < source || value < 1) {
            return;
        }

        if (value == source) {
            answer = Math.min(answer, depth);
            return;
        }

        if (value%2==0) {
            solution(value/2, source, depth+1);
        }

        if (value%2==1) {
            solution((value) / 10, source, depth+1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long source = sc.nextInt();
        long target = sc.nextInt();

        solution(target, source, 0);

        if (answer == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer+1);
        }
    }
}
