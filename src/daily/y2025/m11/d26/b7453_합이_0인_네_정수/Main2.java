package daily.y2025.m11.d26.b7453_합이_0인_네_정수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int size = n * n;

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        long[] ab = new long[size];
        long[] cd = new long[size];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            int ai = a[i];
            int ci = c[i];
            for (int j = 0; j < n; j++) {
                ab[idx] = (long) ai + b[j];
                cd[idx] = (long) ci + d[j];
                idx++;
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        int p1 = 0;          // ab의 시작 인덱스
        int p2 = size - 1;   // cd의 끝 인덱스
        long answer = 0;

        while (p1 < size && p2 >= 0) {
            long sum = ab[p1] + cd[p2];

            if (sum == 0) {
                long v1 = ab[p1];
                long v2 = cd[p2];

                long cnt1 = 0;
                long cnt2 = 0;

                // ab에서 v1과 같은 값 몇 개 있는지 세기
                while (p1 < size && ab[p1] == v1) {
                    cnt1++;
                    p1++;
                }

                // cd에서 v2와 같은 값 몇 개 있는지 세기
                while (p2 >= 0 && cd[p2] == v2) {
                    cnt2++;
                    p2--;
                }

                answer += cnt1 * cnt2;
            } else if (sum < 0) {
                // 합을 키워야 하니까 ab 쪽 인덱스 증가
                p1++;
            } else { // sum > 0
                // 합을 줄여야 하니까 cd 쪽 인덱스 감소
                p2--;
            }
        }

        System.out.println(answer);
    }
}
