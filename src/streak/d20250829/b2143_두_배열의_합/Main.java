package streak.d20250829.b2143_두_배열의_합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int lower(List<Long> arr, long key) {
        int left = 0;
        int right = arr.size() - 1;
        int result = arr.size();

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr.get(mid) >= key) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    static int upper(List<Long> arr, long key) {
        int left = 0;
        int right = arr.size() - 1;
        int result = arr.size();

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr.get(mid) > key) {
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
        int target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[] arr2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> cum1 = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            long s = 0;
            for (int j = i; j < arr1.length; j++) {
                s += arr1[j];
                cum1.add(s);
            }
        }

        List<Long> cum2 = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++) {
            long s = 0;
            for (int j = i; j < arr2.length; j++) {
                s += arr2[j];
                cum2.add(s);
            }
        }

        long answer = 0;
        Collections.sort(cum2);
        for (long val1 : cum1) {
            long gap = target - val1;
            int left = lower(cum2, gap);
            int right = upper(cum2, gap);
            answer += (right - left);
        }

        System.out.println(answer);
    }
}
