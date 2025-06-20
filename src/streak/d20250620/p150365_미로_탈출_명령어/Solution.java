package streak.d20250620.p150365_미로_탈출_명령어;

import java.util.*;

class Solution {

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

        x--; y--; r--; c--;

        int dist = Math.abs(r - x) + Math.abs(c - y);
        if (dist > k || (k - dist) % 2 != 0) {
            return "impossible";
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y, 0, ""));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.cnt == k && node.y == r && node.x == c) {
                answer  = node.path;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }

                int remainMoveCnt = k - node.cnt - 1;
                int newDist = Math.abs(r - ny) + Math.abs(c - nx);

                if (newDist <= remainMoveCnt && (remainMoveCnt - newDist) % 2 == 0) {
                    queue.offer(new Node(ny, nx, node.cnt + 1, node.path + arr[i]));
                    break;
                }
            }
        }

        return answer;
    }
}