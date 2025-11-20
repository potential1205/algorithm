package streak.d20251120.b1944_복제_로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] dist;
    static char[][] board;
    static HashMap<String, Integer> map = new HashMap<>();

    static class Point {
        int y, x;
        int dist;

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

    static class Edge implements Comparable<Edge>{
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static void bfs(int id, int sy, int sx) {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        boolean[][] visit = new boolean[n][n];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(sy, sx, 0));
        visit[sy][sx] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (board[cur.y][cur.x] == 'K' || board[cur.y][cur.x] == 'S') {
                String key = cur.y + ":" + cur.x;
                int targetId = map.get(key);
                dist[id][targetId] = cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] || board[ny][nx] == '1') continue;

                queue.offer(new Point(ny, nx, cur.dist + 1));
                visit[ny][nx] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[m + 1][m + 1];

        int cnt = 0;
        Point[] nodeArr = new Point[m + 1];
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'K' || board[i][j] == 'S') {
                    nodeArr[cnt] = new Point(i, j);
                    String key = i + ":" + j;
                    map.put(key, cnt);
                    cnt++;
                }
            }
        }

        // 각 점들 사이의 최단 경로 구하기
        for (int i = 0; i <= m; i++) {
            bfs(i, nodeArr[i].y, nodeArr[i].x);
        }

        // MST 구하기
        int count = 0;
        int totalCost = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        boolean[] visit = new boolean[m  + 1];

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visit[edge.to]) continue;

            visit[edge.to] = true;
            totalCost += edge.cost;
            count++;

            if (count == m + 1) break;

            for (int i = 0; i <= m; i++) {
                if (edge.to != i && dist[edge.to][i] > 0) {
                    pq.offer(new Edge(i, dist[edge.to][i]));
                }
            }
        }

        if (count == m + 1) {
            System.out.println(totalCost);
        } else {
            System.out.println(-1);
        }
    }
}
