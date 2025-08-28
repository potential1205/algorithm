package streak.d20250827.b1005_ACM_Craft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] time = new int[n + 1];
            int[] in = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                time[i + 1] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                in[to]++;
            }

            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (in[i] == 0) {
                    queue.offer(i);
                }
            }



        }
    }
}
