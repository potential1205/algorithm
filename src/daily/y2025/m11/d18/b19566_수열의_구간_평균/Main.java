package daily.y2025.m11.d18.b19566_수열의_구간_평균;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        HashMap<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);

        long answer = 0;
        long val = 0;
        for (int i = 0; i < n; i++) {
            val  += (arr[i] - k);
            long cnt = map.getOrDefault(val, 0L);
            answer += cnt;
            map.put(val, cnt + 1);
        }

        System.out.println(answer);
    }
}
