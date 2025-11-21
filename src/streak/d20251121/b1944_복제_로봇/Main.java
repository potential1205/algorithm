package streak.d20251121.b1944_복제_로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] board;
    static Point[] points;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] dist;

    static class Node implements Comparable<Node>{
        int num, cost;
        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static class Point {
        int y, x, dist;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Point(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    static void bfs(int id, int sy, int sx) {
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(sy, sx, 0));
        boolean[][] visit = new boolean[n][n];
        visit[sy][sx] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (board[cur.y][cur.x] > 0) {
                int next = board[cur.y][cur.x];
                dist[id][next] = cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] || board[ny][nx] == -1) continue;

                queue.offer(new Point(ny, nx, cur.dist + 1));
                visit[ny][nx] = true;
            }
        }

        //board[sy][sx] = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        points = new Point[m + 2];
        dist = new int[m + 2][m + 2];
        board = new int[n][n];
        int pointCnt = 0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char ch = line.charAt(j);

                if (ch == '1') board[i][j] = -1;
                else if (ch == '0') board[i][j] = 0;
                else if (ch == 'S' || ch == 'K') {
                    pointCnt++;
                    points[pointCnt] = new Point(i, j);
                    board[i][j] = pointCnt;
                }
            }
        }

        // 각 정점에 대해 최단 거리 구하기
        for (int i = 1; i <= m + 1; i++) {
            bfs(i, points[i].y, points[i].x);
        }

        // MST
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        boolean[] visit = new boolean[m + 2];
        int cnt = 0;
        int totalCost = 0;
        int answer = -1;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visit[cur.num]) continue;
            visit[cur.num] = true;
            totalCost += cur.cost;
            cnt++;

            if (cnt == m + 1) {
                answer = totalCost;
            }

            for (int i = 1; i <= m + 1; i++) {
                if (dist[cur.num][i] != 0) {
                    pq.offer(new Node(i, dist[cur.num][i]));
                }
            }
        }

        System.out.println(answer);
    }
}
