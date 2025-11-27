package daily.y2025.m11.d14.b2042_구간_합_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] arr;
    static long[] tree;

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static void update(int node, int start, int end, int idx, long value) {
        if (start == end) {
            tree[node] = value;
            arr[idx] = value;
            return;
        }

        int mid = (start + end) / 2;
        if (idx <= mid) {
            update(node * 2, start, mid, idx, value);
        } else {
            update(node * 2 + 1, mid + 1, end, idx, value);
        }

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right) {

        // 아예 겹치지 않는 경우
        if (left > end || right < start) {
            return 0;
        }

        // 완전 겹치는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 부분 겹치는 경우 (더 쪼개기)
        int mid = (start + end) / 2;
        long leftVal = query(node * 2, start, mid, left, right);
        long rightVal = query(node * 2 + 1, mid + 1, end, left, right);
        return leftVal + rightVal;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }

        tree = new long[4 * n];
        build(1, 1, n);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            // 업데이트
            if (type == 1) {
                int idx = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());
                update(1, 1, n, idx, val);
            }
            // 조회
            else if (type == 2) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long val = query(1, 1, n, left, right);
                sb.append(val).append("\n");
            }
        }

        System.out.println(sb);
    }
}
