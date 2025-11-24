package daily.y2024.m12.d14;

import java.util.Scanner;

public class Main {

    static int N,M;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    static void permutation(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(numbers[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=N; i++){
            numbers[depth] = i;
            permutation(depth+1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        numbers = new int[M];

        permutation(0);

        System.out.println(sb);
    }
}
