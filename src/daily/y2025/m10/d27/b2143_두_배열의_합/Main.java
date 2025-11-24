package daily.y2025.m10.d27.b2143_두_배열의_합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[] arr2 = new int[m];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        long[] cum1 = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            cum1[i] = cum1[i - 1] + arr1[i - 1];
        }

        long[] cum2 = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            cum2[i] = cum2[i - 1] + arr2[i - 1];
        }

        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i - 1; j++) {
                list1.add(cum1[i] - cum1[j]);
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= i - 1; j++) {
                list2.add(cum2[i] - cum2[j]);
            }
        }

        Collections.sort(list1);
        Collections.sort(list2);

        long answer = 0;

        for (long val1 : list1) {
            long val3 = t - val1;

            int lower = lowerBound(val3, list2);
            int upper = upperBound(val3, list2);

            answer += (upper - lower);
        }

        System.out.println(answer);
    }

    static int lowerBound(long val, List<Long> list) {
        int left = 0;
        int right = list.size() - 1;
        int result = list.size();

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) >= val) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    static int upperBound(long val, List<Long> list) {
        int left = 0;
        int right = list.size() - 1;
        int result = list.size();

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) > val) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
