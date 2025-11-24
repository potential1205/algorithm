package daily.y2025.m09.d17.p72415_카드_짝_맞추기;

import java.util.*;

class Solution {

    static int answer = Integer.MAX_VALUE;
    static int cnt;
    static boolean[] sel;
    static int[] val;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Point {
        int y;
        int x;
        int cnt;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    static void permutation(int depth, int[][] board, int r, int c) {
        if (depth == cnt) {

            // 배열 복사
            int[][] temp = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    temp[i][j] = board[i][j];
                }
            }

            // 커서 위치 초기화
            int count = 0;
            Point cursor = new Point(r, c);

            // 카드 순서대로 처리
            for (int i = 0; i < cnt; i++) {
                int card = val[i];

                // 커서로 부터 첫번째 카드까지
                ArrayDeque<Point> queue = new ArrayDeque<>();
                queue.offer(new Point(cursor.y, cursor.x, 0));

                int[][] dp = new int[4][4];
                for (int a = 0; a < 4; a++) {
                    for (int b = 0; b < 4; b++) {
                        dp[a][b] = 10000;
                    }
                }

                while(!queue.isEmpty()) {
                    Point cur = queue.poll();

                    if (temp[cur.y][cur.x] == card) {
                        temp[cur.y][cur.x] = 0;
                        count = count + cur.cnt + 1;
                        cursor.y = cur.y;
                        cursor.x = cur.x;
                        break;
                    }

                    for (int j = 0; j < 4; j++) {
                        int ny = cur.y + dy[j];
                        int nx = cur.x + dx[j];

                        if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue;
                        if (dp[ny][nx] < (cur.cnt + 1)) continue;

                        queue.offer(new Point(ny, nx, cur.cnt + 1));
                        dp[ny][nx] = cur.cnt + 1;
                    }

                    for (int j = 0; j < 4; j++) {
                        int ny = cur.y;
                        int nx = cur.x;

                        while (true) {
                            ny = ny + dy[j];
                            nx = nx + dx[j];

                            if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4) {
                                ny = ny - dy[j];
                                nx = nx - dx[j];
                                break;
                            }

                            if (temp[ny][nx] != 0) {
                                break;
                            }
                        }

                        if (dp[ny][nx] < (cur.cnt + 1)) continue;
                        queue.offer(new Point(ny, nx, cur.cnt + 1));
                        dp[ny][nx] = cur.cnt + 1;
                    }
                }


                // 첫번째카드부터 두번째카드까지
                queue = new ArrayDeque<>();
                queue.offer(new Point(cursor.y, cursor.x, 0));

                dp = new int[4][4];
                for (int a = 0; a < 4; a++) {
                    for (int b = 0; b < 4; b++) {
                        dp[a][b] = 10000;
                    }
                }

                while(!queue.isEmpty()) {
                    Point cur = queue.poll();

                    if (temp[cur.y][cur.x] == card) {
                        temp[cur.y][cur.x] = 0;
                        count = count + cur.cnt + 1;
                        cursor.y = cur.y;
                        cursor.x = cur.x;
                        break;
                    }

                    for (int j = 0; j < 4; j++) {
                        int ny = cur.y + dy[j];
                        int nx = cur.x + dx[j];

                        if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue;
                        if (dp[ny][nx] < (cur.cnt + 1)) continue;

                        queue.offer(new Point(ny, nx, cur.cnt + 1));
                        dp[ny][nx] = cur.cnt + 1;
                    }

                    for (int j = 0; j < 4; j++) {
                        int ny = cur.y;
                        int nx = cur.x;

                        while (true) {
                            ny = ny + dy[j];
                            nx = nx + dx[j];

                            if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4) {
                                ny = ny - dy[j];
                                nx = nx - dx[j];
                                break;
                            }

                            if (temp[ny][nx] != 0) {
                                break;
                            }
                        }

                        if (dp[ny][nx] < (cur.cnt + 1)) continue;
                        queue.offer(new Point(ny, nx, cur.cnt + 1));
                        dp[ny][nx] = cur.cnt + 1;
                    }
                }
            }

            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < cnt; i++) {
            if (sel[i]) continue;
            sel[i] = true;
            val[depth] = i + 1;
            permutation(depth + 1, board, r, c);
            sel[i] = false;
        }
    }

    public int solution(int[][] board, int r, int c) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cnt = Math.max(cnt, board[i][j]);
            }
        }

        sel = new boolean[cnt];
        val = new int[cnt];
        permutation(0, board, r, c);

        return answer;
    }
}