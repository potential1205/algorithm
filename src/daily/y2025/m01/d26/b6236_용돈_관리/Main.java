package daily.y2025.m01.d26.b6236_용돈_관리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer, start, end;
    static int[] costs;

    static void binarySearch() {
        answer = 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            int value = mid; // 잔액
            int cnt = 1; // 인출 횟수

            for (int i = 0; i < N; i++) {
                value -= costs[i];

                if (value < 0) {
                    cnt++;
                    value = mid - costs[i];
                }
            }

            if (cnt > M) {
                start = mid+1;
            } else if (cnt <= M){
                answer = mid;
                end = mid-1;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        costs = new int[N];

        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(bf.readLine());
            costs[i] = Integer.parseInt(st.nextToken());

            start = Math.max(start, costs[i]);
            end += costs[i];
        }

        binarySearch();
    }
}
