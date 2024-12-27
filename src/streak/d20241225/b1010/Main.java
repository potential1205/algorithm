package streak.d20241225.b1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, answer;

    static void solution(int depth, int start) {
        if (depth == n) {
            answer++;
        }

        for (int i = start; i < m; i++) {
            solution(depth + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            answer = 1;

            for(int j = 1; j <= n; j++) {
                answer = answer * (m-j+1) / j;
            }

            //solution(0, 0);
            System.out.println(answer);
        }
    }
}
