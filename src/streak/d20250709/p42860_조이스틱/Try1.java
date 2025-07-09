package streak.d20250709.p42860_조이스틱;

import java.util.*;

class Try1 {

    static int answer;

    static class Node {
        int cnt;
        int idx;
        int depth;

        Node(int idx, int cnt, int depth) {
            this.idx = idx;
            this.cnt = cnt;
            this.depth = depth;
        }
    }

    static void bfs(List<Integer> list, int n) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.depth == list.size()) {
                answer = Math.min(answer, cur.cnt);
                continue;
            }

            int nx1 = (cur.idx + 1) % n;
            int nx2 = (cur.idx + n - 1) % n;

            queue.offer(new Node(nx1, Math.abs(list.get(nx1) - list.get(cur.idx)), cur.depth + 1));
            queue.offer(new Node(nx2, Math.abs(list.get(nx1) - list.get(cur.idx)), cur.depth + 1));

        }
    }

    public int solution(String name) {
        int answer = 0;
        int n = name.length();
        String str = "";

        for (int i = 0; i < n; i++) {
            str += "A";
        }

        int x = 0;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (name.charAt(i) == str.charAt(i)) {
                continue;
            }

            // 문자 차이만큼 이동
            int gap = (int) name.charAt(i) - (int) str.charAt(i);
            gap = Math.min(gap, 26 - gap);
            answer += gap;
            list.add(i);
        }


        int move = n - 1;

        for (int i = 0; i < n; i++) {
            int next = i + 1;

            while (next < n && name.charAt(next) == 'A') {
                next++;
            }

            move = Math.min(move, i + i + (n - next));
            move = Math.min(move, (n - next) + 2 * i);
        }

        return answer + move;
    }
}