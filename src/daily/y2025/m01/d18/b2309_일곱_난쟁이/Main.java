package daily.y2025.m01.d18.b2309_일곱_난쟁이;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] answer = new int[7];

    static int[] input = new int[9];
    static boolean[] visit = new boolean[9];
    static boolean stop;

    static void solve(int depth, int value) {
        if (stop) return;

        if (depth == 7) {
            if (value==100) {
                stop = true;
                Arrays.sort(answer);
                for (int i = 0; i < 7; i++) {
                    System.out.println(answer[i]);
                }
            }

            return;
        }

        for (int i = 0; i < 9; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            answer[depth] = input[i];
            solve(depth+1, value+input[i]);
            visit[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            input[i] = sc.nextInt();
        }

        solve(0, 0);
    }
}
