package daily.y2025.m02.d24.b1388_바닥_장식;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] arr;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0 ,0};
    static int[] dx = {0, 0, -1, 1};

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void dfs(int y, int x, boolean row) {
        visit[y][x] = true;

        if (row) {
            x++;
            if(0 <= x && x < M && arr[y][x] == '-') {
                dfs(y, x, true);
            }
        } else {
            y++;
            if(0 <= y && y<N && arr[y][x] != '-') {
                dfs(y, x, false);
            }
        }

    }

    static void bfs(int sy, int sx, char ch) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sy, sx));
        visit[sy][sx] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            //System.out.println("방문점: " + node.y + " " + node.x);

            for (int i = 0; i < 4; i++) {
                int ky = node.y + dy[i];
                int kx =  node.x + dx[i];

                if (ky < 0 || kx < 0 || ky >= N || kx >= M || visit[ky][kx]) continue;

                if (ch == arr[ky][kx]) {
                    if ((i == 2 || i == 3) && arr[ky][kx] == '-') {
                        queue.offer(new Node(ky, kx));
                        visit[ky][kx] = true;
                    } else if ((i == 0 || i == 1) && arr[ky][kx] != '-') {
                        queue.offer(new Node(ky, kx));
                        visit[ky][kx] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visit[i][j]) continue;

                if(arr[i][j] == '-') {
                    bfs(i, j, arr[i][j]);
                } else {
                    bfs(i, j, arr[i][j]);
                }

                //System.out.println("시작점: " + i + " " + j);
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}