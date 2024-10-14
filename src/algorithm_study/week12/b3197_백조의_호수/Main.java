package algorithm_study.week12.b3197_백조의_호수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, answer;
    static char[][] board;
    static List<Point> pointList;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Point implements Comparable<Point>{
        int y, x, cnt;

        Point(int  y, int x) {
            this.y = y;
            this.x = x;
        }

        Point(int  y, int x, int cnt) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }

    static void bfs(Point p, char sym) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(p);
        board[p.y][p.x] = sym;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                    continue;
                }

                if (board[ny][nx] == 'X' || board[ny][nx] == sym) {
                    continue;
                }

                queue.offer(new Point(ny, nx));
                board[ny][nx] = sym;
            }
        }
    }

    static int bfs2() {
        PriorityQueue<Point> pq = new PriorityQueue();
        boolean[][] visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '1') {
                    pq.offer(new Point(i, j, 0));
                    visit[i][j] = true;
                }
            }
        }

//        while (!pq.isEmpty()) {
//            Point point = pq.poll();
//            System.out.println(point.y + " " + point.x);
//        }

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            System.out.println(cur.y + " " + cur.x + " " + cur.cnt);

            if (board[cur.y][cur.x] == '2') {
                return cur.cnt/2;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx]) {
                    continue;
                }

                if (board[ny][nx] == 'X' || board[ny][nx] == '.') {
                    pq.offer(new Point(ny, nx, cur.cnt+1));
                    visit[ny][nx] = true;
                }
            }

        }

        return 0;
    }

    static void solution() {
        answer = 0;
        bfs(pointList.get(0), '1');
        bfs(pointList.get(1), '2');

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j]);
            }

            System.out.println();
        }

        System.out.println();

        answer = bfs2();

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        pointList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();

            for (int j = 0 ; j < M; j++) {
                board[i][j] = line.charAt(j);

                if (board[i][j] == 'L') {
                    pointList.add(new Point(i,j));
                }
            }
        }

        solution();
    }
}
