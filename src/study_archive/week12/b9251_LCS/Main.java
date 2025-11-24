package study_archive.week12.b9251_LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String str1, str2;
    static int leng1, leng2;
    static int[][] memo;

    static void LCS() {
        for (int i = 1; i <= leng1; i++) {
            for (int j = 1; j <= leng2; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    memo[i][j] = memo[i-1][j-1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
                }
            }
        }
    }

    static void solution() {
        LCS();
        System.out.println(memo[leng1][leng2]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        str1 = bf.readLine();
        str2 = bf.readLine();

        leng1 = str1.length();
        leng2 = str2.length();

        memo = new int[leng1+1][leng2+1];

        solution();
    }
}
