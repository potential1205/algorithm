package streak.d20250323.b2638_치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] board;
    static boolean[][] isOutside;

    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void cheeseCheck() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    int cnt = 0;

                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];

                        if (0 <= ny && ny < n && 0 <= nx && nx < m && board[ny][nx] == 0 && isOutside[ny][nx]) {
                            cnt++;
                        }
                    }

                    if (cnt >= 2) {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    static void outCheck() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));

        isOutside = new boolean[n][m];
        isOutside[0][0] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (0 <= ny && ny < n && 0 <= nx && nx < m && !isOutside[ny][nx] && board[ny][nx] == 0) {
                    queue.offer(new Node(ny, nx));
                    isOutside[ny][nx] = true;
                }
            }
        }
    }

    static boolean isEnd() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isOutside[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());;
            }
        }

        for (int t = 0; t < n * m; t++) {
            outCheck();
            cheeseCheck();

            if (isEnd()) {
                System.out.println(t);
                break;
            }
        }
    }
}
