package streak.d20250829.b14438_수열과_쿼리_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Timeout1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());

            if (cmd == 1) { // 값 변경
                arr[val1 - 1] = val2;
            } else if (cmd == 2) { // 구간의 최소값
                int minVal = Integer.MAX_VALUE;

                for (int j = val1 - 1; j < val2; j++) {
                    minVal = Math.min(minVal, arr[j]);
                }

                sb.append(minVal).append("\n");
            }
        }

        System.out.println(sb);
    }
}
