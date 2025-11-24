package daily.y2025.m04.d06.b1233_주사위;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int s1 = Integer.parseInt(input[0]);
        int s2 = Integer.parseInt(input[1]);
        int s3 = Integer.parseInt(input[2]);

        int[] counts = new int[s1 + s2 + s3 + 1];

        for (int i = 1; i <= s1; i++) {
            for (int j = 1; j <= s2; j++) {
                for (int k = 1; k <= s3; k++) {
                    counts[i + j + k]++;
                }
            }
        }

        int answer = 0;
        int maxCnt = 0;

        for (int val = 3; val < counts.length; val++) {
            if (counts[val] > maxCnt) {
                maxCnt = counts[val];
                answer = val;
            }
        }

        System.out.println(answer);
    }
}
