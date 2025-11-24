package daily.y2025.m03.d16.b16724_피리_부는_사나이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] board;
    static boolean[][] visit;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int answer;

    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int getIndex(char ch) {
        if (ch == 'U') return 0;
        else if (ch == 'D') return 1;
        else if (ch == 'L') return 2;
        else if (ch == 'R') return 3;
        return -1;
    }

    static boolean isPointingToMe(int d, int ny, int nx) {
        return (d == 0 && board[ny][nx] == 'D') ||
                (d == 1 && board[ny][nx] == 'U') ||
                (d == 2 && board[ny][nx] == 'R') ||
                (d == 3 && board[ny][nx] == 'L');
    }

    static void search(int sy, int sx) {
        Node initNode = new Node(sy, sx);
        Queue<Node> queue = new LinkedList<>();

        queue.offer(initNode);
        visit[sy][sx] = true;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            int idx = getIndex(board[curNode.y][curNode.x]);

            for (int i = 0; i < 4; i++) {
                int ny = curNode.y + dy[i];
                int nx = curNode.x + dx[i];

                if (0 <= ny && ny < n && 0 <= nx && nx < m && !visit[ny][nx]) {
                    if (isPointingToMe(i, ny, nx) || i == idx) {
                        Node nextNode = new Node(ny, nx);
                        queue.offer(nextNode);
                        visit[ny][nx] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    search(i, j);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
