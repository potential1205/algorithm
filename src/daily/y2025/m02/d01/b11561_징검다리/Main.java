package daily.y2025.m02.d01.b11561_징검다리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int  tc = 0; tc < T; tc++) {
            st = new StringTokenizer(bf.readLine());

            long n = Long.parseLong(st.nextToken());

            long left = 0;
            long right = (long) Math.sqrt((2*n-1));;
            long answer = 0;
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long sum = mid * (mid+1) / 2;

                if (sum > n) {
                    right = mid-1;
                } else {
                    left = mid+1;
                    answer = Math.max(answer, mid);
                }
            }

            if (n==1) {
                System.out.println(1);
            } else {
                System.out.println(answer);
            }
        }
    }
}
