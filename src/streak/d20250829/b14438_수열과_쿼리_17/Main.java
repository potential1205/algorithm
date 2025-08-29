package streak.d20250829.b14438_수열과_쿼리_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] seg;
    static int[] arr;

    static void build(int node, int left, int right) {
        if (left == right) {
            seg[node] = arr[left];
            return;
        }

        int mid = (left + right) / 2;
        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);
        seg[node] = Math.min(seg[node * 2], seg[node * 2 + 1]);
    }

    static void update(int node, int left, int right, int idx, int val) {
        if (idx < left || right < idx) {
            return;
        }

        if (left == right) {
            seg[node] = val;
            return;
        }

        int mid = (left + right) / 2;
        if (idx <= mid) {
            update(node * 2, left, mid, idx, val);
        } else {
            update(node * 2 + 1, mid + 1, right, idx, val);
        }
        seg[node] = Math.min(seg[node * 2], seg[node * 2 + 1]);
    }

    static int query(int node, int left, int right, int r1, int r2) {
        if (right < r1 || r2 < left) return Integer.MAX_VALUE;
        if (r1 <= left && right <= r2) return seg[node];
        int mid = (left + right) / 2;
        int leftMin = query(node * 2, left, mid, r1, r2);
        int rightMin = query(node * 2 + 1, mid + 1, right, r1, r2);
        return Math.min(leftMin, rightMin);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        seg = new int[4 * n];
        build(1, 1, n);

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());

            if (cmd == 1) { // 값 변경
                update(1, 1, n, val1, val2);
            } else if (cmd == 2) { // 구간의 최소값
                int minVal = query(1, 1, n, val1, val2);
                sb.append(minVal).append("\n");
            }
        }

        System.out.println(sb);
    }
}
