package daily.y2024.m12.d24.b10819;

import java.util.Scanner;

public class Main {

    static int n, answer;
    static boolean[] visited;
    static int[] temp, arr;

    static void solution(int depth) {
        if (depth==n) {
            int value = 0;
            for (int i = 0; i < n-1; i++) {
                value += (Math.abs(temp[i] - temp[i+1]));
            }

            answer = Math.max(answer, value);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[depth] = arr[i];
                solution(depth+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        visited = new boolean[n];
        temp = new int[n];
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        solution(0);

        System.out.println(answer);
    }
}

