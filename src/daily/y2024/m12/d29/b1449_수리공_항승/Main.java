package daily.y2024.m12.d29.b1449_수리공_항승;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,L,answer;
    static int tape;
    static int[] holes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        holes = new int[N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            holes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(holes);

        for (int i = 0; i < N; i++) {
            if (holes[i] > tape) {
                tape = holes[i] + L - 1;
                answer++;
            }
        }

        System.out.println(answer);
    }
}
