package streak.d20251111.b23326_홍익_투어리스트;

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

        int cur = 0;
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int queryType = Integer.parseInt(st.nextToken());

            if (queryType == 1) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                if (set.contains(idx)) {
                    set.remove(idx);
                } else {
                    set.add(idx);
                }

            } else if (queryType == 2) {
                int x = Integer.parseInt(st.nextToken()) % n;
                cur = (cur + x) % n;

            } else if (queryType == 3){
                if (set.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    Integer next = set.ceiling(cur);
                    if (next == null) next = set.first();
                    // 현재 위치에서 한 바퀴 돌고 cur빼주면 0번째 위치로 기준이 잡히고 이후 next 만큼 이동
                    int dist = (n - cur + next) % n;
                    sb.append(dist).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}
