package streak.d20251114.b14722_우유_도시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Timeout {

    static int n;
    static int[][] arr, dp;

    static class Node {
        int y, x;
        int cnt;
        int milk;

        Node(int y, int x, int cnt, int milk) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.milk = milk;
        }
    }

    static void bfs() {
        int[] dy = {1, 0};
        int[] dx = {0, 1};

        ArrayDeque<Node> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    queue.offer(new Node(i, j, 1, 0));
                    dp[i][j] = 1;
                }
            }
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 2; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;

                if (cur.milk == 0 && arr[ny][nx] == 1 && dp[ny][nx] < cur.cnt + 1) {
                    dp[ny][nx] = cur.cnt + 1;
                    queue.offer(new Node(ny, nx, cur.cnt + 1, arr[ny][nx]));

                } else if (cur.milk == 1 && arr[ny][nx] == 2 && dp[ny][nx] < cur.cnt + 1) {
                    dp[ny][nx] = cur.cnt + 1;
                    queue.offer(new Node(ny, nx, cur.cnt + 1, arr[ny][nx]));

                } else if (cur.milk == 2 && arr[ny][nx] == 0 && dp[ny][nx] < cur.cnt + 1) {
                    dp[ny][nx] = cur.cnt + 1;
                    queue.offer(new Node(ny, nx, cur.cnt + 1, arr[ny][nx]));
                } else if (dp[ny][nx] < cur.cnt){
                    dp[ny][nx] = cur.cnt;
                    queue.offer(new Node(ny, nx, cur.cnt, cur.milk));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        dp = new int[n][n];
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.println(dp[n - 1][n - 1]);
    }
}
