package streak.d20250312.b12891_비밀번호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int answer;
    static String str;
    static int[] source;
    static int[] target;

    static boolean check() {
        if (source[0] >= target[0] && source[1] >= target[1] && source[2] >= target[2] && source[3] >= target[3])  {
            return true;
        }

        return false;
    }

    static int getIdx(char ch) {
        if (ch == 'A') return 0;
        else if (ch == 'C') return 1;
        else if (ch == 'G') return 2;
        else if (ch == 'T') return 3;

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        str = br.readLine();
        source = new int[4];
        target = new int[4];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            int idx = getIdx(str.charAt(i));
            if (idx != -1) {
                source[idx]++;
            }
        }

        for (int i = 0; i < 4; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        if (check()) {
            answer++;
        }

        for (int i = m; i < n; i++) {
            int idx = getIdx(str.charAt(i));
            if (idx != -1) {
                source[idx]++;
            }

            idx = getIdx(str.charAt(i-m));
            if (idx != -1) {
                source[idx]--;
            }

            if (check()) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
