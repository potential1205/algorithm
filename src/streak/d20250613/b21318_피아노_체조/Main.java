package streak.d20250613.b21318_피아노_체조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] pianoInfo = new int[n];
        int[] pianoPrefixSum = new int[n];
        pianoPrefixSum[0] = 0;

        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < n; i++) {
            pianoInfo[i] = Integer.parseInt(st.nextToken());

            if (i > 0) {
                if (pianoInfo[i - 1] > pianoInfo[i]) {
                    pianoPrefixSum[i] = pianoPrefixSum[i - 1] + 1;
                } else {
                    pianoPrefixSum[i] = pianoPrefixSum[i - 1];
                }
            }
        }

        st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int startIdx = Integer.parseInt(st.nextToken()) - 1;
            int endIdx = Integer.parseInt(st.nextToken()) - 1;

            sb.append(pianoPrefixSum[endIdx] - pianoPrefixSum[startIdx]).append("\n");
        }

        System.out.println(sb);
    }
}
