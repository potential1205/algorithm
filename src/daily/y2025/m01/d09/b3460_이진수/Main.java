package daily.y2025.m01.d09.b3460_이진수;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int T, N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();

            int digit = 0;
            int copyN = N;

            List<Integer> list = new ArrayList<>();

            while (copyN > 0) {
                digit++;
                copyN /= 2;
            }

            for (int i = 0; i < digit; i++) {
                int value = N & (int) (Math.pow(2, i));
                if (value != 0) {
                    list.add(i);
                }
            }

            for (int value : list) {
                System.out.print(value + " ");
            }
        }

    }
}
