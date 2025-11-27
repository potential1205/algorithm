package daily.y2025.m11.d27.r1.b7453_합이_0인_네_정수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int underBound(long[] arr, long target, int size) {
        int left = 0;
        int right = size - 1;
        int result = size;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target <= arr[mid]) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    static int upperBound(long[] arr, long target, int size) {
        int left = 0;
        int right = size - 1;
        int result = size;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < arr[mid]) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int size = n * n;

        long[] a = new long[n];
        long[] b = new long[n];
        long[] c = new long[n];
        long[] d = new long[n];
        long[] ab = new long[size];
        long[] cd = new long[size];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        HashMap<Long, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[cnt] = a[i] + b[j];
                cd[cnt] = c[i] + d[j];
                map.put(ab[cnt], map.getOrDefault(ab[cnt], 0L) + 1);
                cnt++;
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        long answer = 0;
        for (int i = 0; i < cnt; i++) {
            long val1 = ab[i];
            long val2 = -val1;
            int left = underBound(cd, val2, cnt);
            int right = upperBound(cd, val2, cnt);
            answer += (right - left);
        }

        System.out.println(answer);
    }
}
