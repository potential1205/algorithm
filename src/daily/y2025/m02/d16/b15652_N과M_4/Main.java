package daily.y2025.m02.d16.b15652_N과M_4;

import java.util.Scanner;

public class Main {

    static int n,m;
    static int[] values;
    static StringBuilder sb = new StringBuilder();

    static void back(int depth, int start) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(values[i]).append(" ");
            }

            sb.append("\n");

            return;
        }

        for (int i = start; i < n; i++) {
            values[depth] = i+1;
            back(depth+1, i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        values = new int[m];

        back(0, 0);

        System.out.println(sb);
    }

}
