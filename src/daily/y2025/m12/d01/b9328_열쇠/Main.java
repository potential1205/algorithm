package daily.y2025.m12.d01.b9328_열쇠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer;
    static int n, m;
    static char[][] board;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[] keys;
    static boolean[][] visit;

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void bfs() {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        visit[0][0] = true;

        List<Node>[] list = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            list[i] = new ArrayList<>();
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= (n + 2) || nx >= (m + 2)) continue;
                if (board[ny][nx] == '*') continue;
                if (visit[ny][nx]) continue;

                char ch = board[ny][nx];

                if (ch == '$') {
                    answer++;
                    board[ny][nx] = '.';
                    queue.offer(new Node(ny, nx));
                    visit[ny][nx] = true;
                } else if ('A' <= ch && ch <= 'Z') {
                    int idx = ch - 'A';
                    if (keys[idx]) {
                        board[ny][nx] = '.';
                        queue.offer(new Node(ny, nx));
                        visit[ny][nx] = true;
                    } else {
                        list[idx].add(new Node(ny, nx));
                    }
                } else if ('a' <= ch && ch <= 'z') {
                    int idx = ch - 'a';
                    keys[idx] = true;
                    board[ny][nx] = '.';
                    queue.offer(new Node(ny, nx));
                    visit[ny][nx] = true;

                    for (Node node : list[idx]) {
                        queue.offer(new Node(node.y, node.x));
                        visit[node.y][node.x] = true;
                    }
                } else if (board[ny][nx] == '.') {
                    queue.offer(new Node(ny, nx));
                    visit[ny][nx] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            keys = new boolean[26];
            board = new char[n + 2][m + 2];
            visit = new boolean[n + 2][m + 2];
            answer = 0;

            // 마진
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < m + 2; j++) {
                    board[i][j] = '.';
                }
            }

            // 보드 값 넣기
            for (int i = 1; i < n + 1; i++) {
                String line = br.readLine();
                for (int j = 1; j < m + 1; j++) {
                    board[i][j] = line.charAt(j - 1);
                }
            }

            // 초기 키 저장
            String line = br.readLine();
            for (char key : line.toCharArray()) {
                if (key == '0') break;
                int idx = key - 'a';
                keys[idx] = true;
            }

            bfs();
            System.out.println(answer);
        }
    }
}
