package streak.d20250813.p60061_기둥과_보_설치;

import java.util.*;

class Solution {
    static int N;
    static boolean[][][] board;

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int type;

        Node(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x - o.x != 0) {
                return this.x - o.x;
            }

            if (this.y - o.y != 0) {
                return this.y - o.y;
            }

            return this.type - o.type;
        }
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x <= N && 0 <= y && y <= N;
    }

    static boolean hasPillar(int x, int y) {
        return inRange(x, y) && board[x][y][0];
    }

    static boolean hasBeam(int x, int y) {
        return inRange(x, y) && board[x][y][1];
    }

    static boolean isValidPillar(int x, int y) {
        if (!hasPillar(x, y)) {
            return true;
        }
        return y == 0 || hasBeam(x - 1, y) || hasBeam(x, y) || hasPillar(x, y - 1);
    }

    static boolean isValidBeam(int x, int y) {
        if (!hasBeam(x, y)) {
            return true;
        }
        return hasPillar(x, y - 1) || hasPillar(x + 1, y - 1) || (hasBeam(x - 1, y) && hasBeam(x + 1, y));
    }

    static boolean isAllValid() {
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (!isValidPillar(x, y)) {
                    return false;
                }
                if (!isValidBeam(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] solution(int n, int[][] buildFrame) {
        N = n;
        board = new boolean[N + 1][N + 1][2];

        for (int[] cmd : buildFrame) {
            int x = cmd[0];
            int y = cmd[1];
            int a = cmd[2];
            int b = cmd[3];

            if (b == 1) {
                board[x][y][a] = true;
                if (!isAllValid()) {
                    board[x][y][a] = false;
                }
            } else {
                board[x][y][a] = false;
                if (!isAllValid()) {
                    board[x][y][a] = true;
                }
            }
        }

        List<Node> list = new ArrayList<>();
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                for (int a = 0; a < 2; a++) {
                    if (board[x][y][a]) {
                        list.add(new Node(x, y, a));
                    }
                }
            }
        }

        Collections.sort(list);

        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            answer[i][0] = node.x;
            answer[i][1] = node.y;
            answer[i][2] = node.type;
        }
        return answer;
    }
}
