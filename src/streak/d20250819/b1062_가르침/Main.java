package streak.d20250819.b1062_가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, k, answer, needCnt;
    static int[] mask;
    static int[] base = {'a','n','t','i','c'};

    static void combination(int depth, int start, int value) {
        if (depth == needCnt) {
            int result = 0;

            for (int i = 0; i < n; i++) {
                if ((mask[i] & value) == mask[i]) {
                    result++;
                }
            }

            answer = Math.max(answer ,result);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((value & (1 << i)) != 0) continue;

            value |= (1 << i);
            combination(depth + 1, i + 1, value);
            value &= ~(1 << i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        mask = new int[n];
        needCnt = k - 5;

        if (k < 5) {
            System.out.println(0);
            return;
        }

        if (k == 26) {
            System.out.println(n);
            return;
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            int value = 0;
            for (char ch : str.toCharArray()) {
                int bit = ch - 'a';
                value |= (1 << bit);
            }

            mask[i] = value;
        }

        int baseMask = 0;
        for (int ch : base) {
            baseMask |= (1 << (ch - 'a'));
        }

        combination(0, 0, baseMask);

        System.out.println(answer);
    }
}
