package daily.y2025.m09.d03.p60063_블록_이동하기;

import java.util.*;

class Solution {

    static class Robot {
        int y;
        int x;
        int d;
        int time;

        Robot(int y, int x, int d, int time) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.time = time;
        }
    }

    public int solution(int[][] board) {
        int answer = 0;

        int n = board.length;
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }

        dp[0][0][0] = 0;

        ArrayDeque<Robot> queue = new ArrayDeque<>();
        queue.offer(new Robot(0, 0, 0, 0));

        // 큐 관리
        while (!queue.isEmpty()) {
            Robot robot = queue.poll();

            // 종료 조건
            if (robot.d == 0 && robot.y == (n - 1) && robot.x == (n - 2)) {
                return robot.time;
            }

            if (robot.d == 1 && robot.y == (n - 2) && robot.x == (n - 1)) {
                return robot.time;
            }

            int y = robot.y;
            int x = robot.x;
            int d = robot.d;
            int time = robot.time;

            // 오른쪽 이동
            if (d == 0) {
                if (x + 2 < n && board[y][x + 2] == 0) {
                    if (dp[y][x + 1][0] > robot.time + 1) {
                        dp[y][x + 1][0] = robot.time + 1;
                        queue.offer(new Robot(y, x + 1, d, time + 1));
                    }
                }
            } else if (d == 1) {
                if (x + 1 < n && board[y][x + 1] == 0 && board[y + 1][x + 1] == 0) {
                    if (dp[y][x + 1][1] > robot.time + 1) {
                        dp[y][x + 1][1] = robot.time + 1;
                        queue.offer(new Robot(y, x + 1, d, time + 1));
                    }
                }
            }

            // 왼쪽 이동
            if (d == 0) {
                if (x - 1 >= 0 && board[y][x - 1] == 0) {
                    if (dp[y][x - 1][0] > robot.time + 1) {
                        dp[y][x - 1][0] = robot.time + 1;
                        queue.offer(new Robot(y, x - 1, 0, time + 1));
                    }
                }
            } else if (d == 1) {
                if (x - 1 >= 0 && board[y][x - 1] == 0 && board[y + 1][x - 1] == 0) {
                    if (dp[y][x - 1][1] > robot.time + 1) {
                        dp[y][x - 1][1] = robot.time + 1;
                        queue.offer(new Robot(y, x - 1, 1, time + 1));
                    }
                }
            }

            // 위로 이동
            if (d == 0) {
                if (y - 1 >= 0 && board[y - 1][x] == 0 && board[y - 1][x + 1] == 0) {
                    if (dp[y - 1][x][0] > robot.time + 1) {
                        dp[y - 1][x][0] = robot.time + 1;
                        queue.offer(new Robot(y - 1, x, 0, time + 1));
                    }
                }
            } else if (d == 1) {
                if (y - 1 >= 0 && board[y - 1][x] == 0) {
                    if (dp[y - 1][x][1] > robot.time + 1) {
                        dp[y - 1][x][1] = robot.time + 1;
                        queue.offer(new Robot(y - 1, x, 1, time + 1));
                    }
                }
            }

            // 아래 이동
            if (d == 0) {
                if (y + 1 < n && board[y + 1][x] == 0 && board[y + 1][x + 1] == 0) {
                    if (dp[y + 1][x][0] > robot.time + 1) {
                        dp[y + 1][x][0] = robot.time + 1;
                        queue.offer(new Robot(y + 1, x, d, time + 1));
                    }
                }
            } else if (d == 1) {
                if (y + 2 < n && board[y + 2][x] == 0) {
                    if (dp[y + 1][x][1] > robot.time + 1) {
                        dp[y + 1][x][1] = robot.time + 1;
                        queue.offer(new Robot(y + 1, x, d, time + 1));
                    }
                }
            }

            // 회전
            if (d == 0) {
                // 아래로 좌회전
                if (y + 1 < n && board[y + 1][x] == 0 && board[y + 1][x + 1] == 0) {
                    if (dp[y][x][1] > robot.time + 1) {
                        dp[y][x][1] = robot.time + 1;
                        queue.offer(new Robot(y, x, 1, time + 1));
                    }
                }

                // 위로 좌회전
                if (y - 1 >= 0 && board[y - 1][x] == 0 && board[y - 1][x + 1] == 0) {
                    if (dp[y - 1][x][1] > robot.time + 1) {
                        dp[y - 1][x][1] = robot.time + 1;
                        queue.offer(new Robot(y - 1, x, 1, time + 1));
                    }
                }

                // 위로 우회전
                if (y - 1 >= 0 && board[y - 1][x] == 0 && board[y - 1][x + 1] == 0) {
                    if (dp[y - 1][x + 1][1] > robot.time + 1) {
                        dp[y - 1][x + 1][1] = robot.time + 1;
                        queue.offer(new Robot(y - 1, x + 1, 1, time + 1));
                    }
                }

                // 아래로 우회전
                if (y + 1 < n && board[y + 1][x] == 0 && board[y + 1][x + 1] == 0) {
                    if (dp[y][x + 1][1] > robot.time + 1) {
                        dp[y][x + 1][1] = robot.time + 1;
                        queue.offer(new Robot(y, x + 1, 1, time + 1));
                    }
                }

            } else if (d == 1) {
                // 아래로 좌회전
                if (x - 1 >= 0 && board[y + 1][x - 1] == 0 && board[y][x - 1] == 0) {
                    if (dp[y + 1][x - 1][0] > robot.time + 1) {
                        dp[y + 1][x - 1][0] = robot.time + 1;
                        queue.offer(new Robot(y + 1, x - 1, 0, time + 1));
                    }
                }

                // 위로 좌회전
                if (x - 1 >= 0 && board[y][x - 1] == 0 && board[y + 1][x - 1] == 0) {
                    if (dp[y][x - 1][0] > robot.time + 1) {
                        dp[y][x - 1][0] = robot.time + 1;
                        queue.offer(new Robot(y, x - 1, 0, time + 1));
                    }
                }

                // 아래로 우회전
                if (x + 1 < n && board[y + 1][x + 1] == 0 && board[y][x + 1] == 0) {
                    if (dp[y + 1][x][0] > robot.time + 1) {
                        dp[y + 1][x][0] = robot.time + 1;
                        queue.offer(new Robot(y + 1, x, 0, time + 1));
                    }
                }

                // 위로 우회전
                if (x + 1 < n && board[y][x + 1] == 0 && board[y + 1][x + 1] == 0) {
                    if (dp[y][x][0] > robot.time + 1) {
                        dp[y][x][0] = robot.time + 1;
                        queue.offer(new Robot(y, x, 0, time + 1));
                    }
                }
            }
        }

        return answer;
    }
}