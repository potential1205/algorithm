package daily.y2025.m01.d01.b1339_단어_수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer;
    static String[] words;
    static int[] values;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        words = new String[N];
        values = new int[26];

        for (int i = 0; i < N; i++) {
            words[i] = bf.readLine();

            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                int index = ch - 'A';
                values[index] += Math.pow(10, words[i].length() - j - 1);
            }
        }

        Arrays.sort(values);

        int num = 9;

        for (int i = 25; i >= 0; i--) {
            if (values[i]==0) break;

            answer += (num * values[i]);
            num--;
        }

        System.out.println(answer);
    }
}
