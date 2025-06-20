package streak.d20250620.p150365_미로_탈출_명령어;

import java.util.*;

// 시간초과 발생
class Try1 {

    static char[] arr = {'d', 'l', 'r', 'u'};
    static int[] dy = {1, 0, 0, -1};
    static int[] dx = {0, -1, 1, 0};

    static class Node implements Comparable<Node> {
        int y;
        int x;
        int cnt;
        String path;

        Node() {}

        Node(int y, int x, int cnt, String path) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.path = path;
        }

        @Override
        public int compareTo(Node o) {
            if (o.cnt == this.cnt) {
                return o.cnt - this.cnt;
            } else {
                return this.path.compareTo(o.path);
            }
        }
    }


    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x - 1, y - 1, 0, ""));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.cnt == k && node.y == (r-1) && node.x == (c-1)) {
                answer  = node.path;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m || node.cnt >= k) {
                    continue;
                }

                queue.offer(new Node(ny, nx, node.cnt + 1, node.path + arr[i]));
            }
        }

        if (answer == "") {
            answer = "impossible";
        }

        return answer;
    }
}