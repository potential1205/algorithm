package streak.d20251112.b23326_홍익_투어리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        TreeSet<Integer> set = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            if (val == 1) {
                set.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        int cur = 0;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                if (set.contains(idx)) {
                    set.remove(idx);
                } else {
                    set.add(idx);
                }

            } else if (type == 2) {
                int x = Integer.parseInt(st.nextToken());
                cur = (cur + x) % n;

            } else if (type == 3) {
                if (set.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    Integer next = set.ceiling(cur);
                    if (next == null) {
                        next = set.first();
                        sb.append(n - cur + next).append("\n");
                    } else {
                        sb.append(next - cur).append("\n");
                    }
                }
            }
        }

        System.out.println(sb);
    }
}
