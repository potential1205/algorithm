package streak.d20250326.b5568_카드_놓기;

import java.util.*;

public class Main {

    static Set<String> set = new HashSet<>();
    static List<String> list = new ArrayList<>();
    static boolean[] visit;
    static int n, m;

    static void solve(int depth, String val) {
        if (depth == m) {
            set.add(val);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                solve(depth + 1, val + list.get(i));
                visit[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            list.add(sc.next());
        }

        solve(0, "");

        System.out.println(set.size());
    }
}
