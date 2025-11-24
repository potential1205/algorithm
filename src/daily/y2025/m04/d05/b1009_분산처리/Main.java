package daily.y2025.m04.d05.b1009_분산처리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] cases = {
            {10, 10, 10, 10},
            {1, 1, 1, 1},
            {2, 4, 8, 6},
            {3, 9, 7, 1},
            {4, 6, 4, 6},
            {5, 5, 5, 5},
            {6, 6, 6, 6},
            {7, 9, 3, 1},
            {8, 4, 2, 6},
            {9, 1, 9, 1},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(cases[a % 10][(b-1) % 4]).append("\n");
        }

        System.out.println(sb);
    }
}
