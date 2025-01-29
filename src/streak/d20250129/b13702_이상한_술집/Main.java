package streak.d20250129.b13702_이상한_술집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long answer = 0;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long left = 1;
        long right = arr[n-1];

        while (left <= right) {
            long mid = (left + right) / 2;

            int cnt = 0;

            for (int i = 0; i < n; i++) {
                cnt += (int) (arr[i]/mid);
            }

            if (cnt >= k) {
                left = mid+1;
                answer = mid;
            } else {
                right = mid-1;
            }
        }

        System.out.println(answer);
    }
}
