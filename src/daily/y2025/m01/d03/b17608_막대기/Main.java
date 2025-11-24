package daily.y2025.m01.d03.b17608_막대기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, maxH, answer;
    static int[] rods;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        rods = new int[N];

        for (int i = 0; i < N; i++) {
            rods[i] = Integer.parseInt(bf.readLine());
        }

        answer = 1;
        maxH = rods[N-1];

        for (int i = N-2; i >=0; i--) {
            if (rods[i] > maxH) {
                answer++;
                maxH = rods[i];
            }
        }

        System.out.println(answer);
    }
}
