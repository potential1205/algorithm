package daily.y2025.m01.d31.b1743_음식물피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] visit;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int n,m,k;
    static int[][] arr;

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int bfs(int y, int x) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(y,x));
        int cnt = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int ky = node.y + dy[i];
                int kx = node.x + dx[i];

                if (ky<0 || kx<0 || ky>=n || kx>=m || visit[ky][kx] || arr[ky][kx]==0) {
                    continue;
                }

                visit[ky][kx] = true;
                queue.offer(new Node(ky,kx));
            }

        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            arr[r][c] = 1;
        }

        visit = new boolean[n][m];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j] && arr[i][j]==1) {
                    int cnt = bfs(i,j);
                    answer = Math.max(answer ,cnt);
                }
            }
        }

        System.out.println(answer-1);
    }
}
