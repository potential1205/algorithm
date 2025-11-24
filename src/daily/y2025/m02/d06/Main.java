package daily.y2025.m02.d06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] board;
    static boolean[][] visit;
    static int n,m;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int yCnt, nCnt;

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void bfs(int sy, int sx) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sy, sx));
        visit[sy][sx] = true;

        int a = 0; // 양
        int b = 0; // 늑대

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if (board[node.y][node.x]=='v') {
                b++;
            } else if (board[node.y][node.x]=='k') {
                a++;
            }

            for (int i = 0; i < 4; i++) {
                int ky = node.y + dy[i];
                int kx = node.x + dx[i];

                if (ky < 0 || kx < 0 || ky>=n || kx >= m || visit[ky][kx] || board[ky][kx] == '#') continue;

                visit[ky][kx] = true;
                queue.offer(new Node(ky, kx));
            }
        }

        if (b>=a) {
            nCnt += b;
        } else {
            yCnt += a;
        }
    }

    static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j] || board[i][j] == '#') continue;
                bfs(i,j);
            }
        }

        System.out.println(yCnt + " " + nCnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = bf.readLine();

            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        solve();
    }
}
