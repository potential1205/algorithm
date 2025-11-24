package daily.y2025.m08.d13.p60061_기둥과_보_설치;

import java.util.*;

class Try1 {
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

    static boolean removePillar(int x, int y) {
        // 제거하려는 기둥위에 기둥이 있는 경우
        if (0 <= (x - 1) && (y + 1) <= N && board[x][y + 1][0] && !board[x - 1][y + 1][1] && !board[x][y + 1][1]) {
            return false;
        }

        // 제거하려는 기둥위에 보가 있는 경우
        if ((0 <= (x - 1) && (x + 1) <= N && (y + 1) <= N && board[x][y + 1][1] && !board[x + 1][y][0]) &&
                (board[x][y + 1][1] && !board[x + 1][y + 1][1] && !board[x - 1][y + 1][1])) {
            return false;
        }

        if (0 <= (x - 1) && (y + 1) <= N && 0 <= (x - 2) && (board[x - 1][y + 1][1] && !board[x - 1][y][0]) &&
                (board[x - 1][y + 1][1] && !board[x][y + 1][1] && !board[x - 2][y + 1][1])) {
            return false;
        }

        board[x][y][0] = false;
        return true;
    }

    static boolean installPillar(int x, int y) {
        // 바닥인지
        if (y == 0) {
            board[x][y][0] = true;
            return true;
        }

        // 보의 한쪽 끝 부분인지
        if (0 <= (x - 1) && board[x - 1][y][1]) {
            board[x][y][0] = true;
            return true;
        }

        if (board[x][y][1]) {
            board[x][y][0] = true;
            return true;
        }

        // 다른 기둥위인지
        if (0 <= (y - 1) && board[x][y - 1][0]) {
            board[x][y][0] = true;
            return true;
        }

        return false;
    }

    static boolean removeBeam(int x, int y) {
        // 보 위에 기둥이 있는 경우
        if (0 <= (y - 1) && (x + 1) <= N && board[x + 1][y][0] && !board[x + 1][y - 1][0] &&
                board[x + 1][y][0] && !board[x + 1][y][1]) {
            return false;
        }

        if (0 <= (x - 1) && 0 <= (y - 1) && board[x][y][0] && !board[x][y - 1][0] &&
                board[x][y][0] && !board[x - 1][y][1]) {
            return false;
        }

        // 보가 보로 연결되어 있는 경우
        if (0 <= (x - 2) && 0 <= (y - 1) && 0 <= (x - 1) && board[x - 1][y][1] && !board[x - 1][y - 1][0] &&
                board[x - 1][y][1] && !board[x - 2][y - 1][0]) {
            return false;
        }

        if ( (x + 2) <= N && 0 <= (y - 1) && (x + 1) <= N && board[x + 1][y][1] && !board[x + 1][y - 1][0] &&
                board[x + 1][y][1] && !board[x + 2][y - 1][0]) {
            return false;
        }

        board[x][y][1] = false;
        return true;
    }

    static boolean installBeam(int x, int y)  {
        // 한쪽 끝이 기둥 위인지
        if ((x + 1) <= N && 0 <= (y - 1) && board[x + 1][y - 1][0]) {
            board[x][y][1] = true;
            return true;
        }

        if (0 <= (y - 1) && board[x][y - 1][0]) {
            board[x][y][1] = true;
            return true;
        }

        // 양쪽 끝이 다른 보와 연결되었는지
        if (0 <= (x - 1) && (x + 1) <= N && board[x - 1][y][1] && board[x + 1][y][1]) {
            board[x][y][1] = true;
            return true;
        }

        return false;
    }

    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        board = new boolean[n + 1][n + 1][2];

        for (int i = 0; i < build_frame.length; i++) {
            int[] line = build_frame[i];

            if (line[2] == 0 && line[3] == 0) { // 기둥 삭제
                System.out.println("기둥 삭제 : " + line[0] + " " + line[1]);
                if (removePillar(line[0], line[1])) {
                    System.out.println("기둥 삭제 성공");
                }
            } else if (line[2] == 0 && line[3] == 1) { // 기둥 설치
                System.out.println("기둥 설치 : " + line[0] + " " + line[1]);
                if (installPillar(line[0], line[1])) {
                    System.out.println("기둥 설치 성공");
                }
            } else if (line[2] == 1 && line[3] == 0) { // 보 삭제
                System.out.println("보 삭제 : " + line[0] + " " + line[1]);
                if (removeBeam(line[0], line[1])) {
                    System.out.println("보 삭제 성공");
                }
            } else if (line[2] == 1 && line[3] == 1) { // 보 설치
                System.out.println("보 설치 : " + line[0] + " " + line[1]);
                if (installBeam(line[0], line[1])) {
                    System.out.println("보 설치 성공");
                }
            }
        }

        List<Node> list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < 2; k++) {
                    if (board[i][j][k]) {
                        list.add(new Node(i, j, k));
                    }
                }
            }
        }

        Collections.sort(list);

        int[][] answer = new int[list.size()][3];
        int idx = 0;
        for (Node node : list) {
            answer[idx][0] = node.x;
            answer[idx][1] = node.y;
            answer[idx][2] = node.type;
            idx++;
            System.out.println(node.x + " " + node.y + " " + node.type);
        }

        return answer;
    }
}