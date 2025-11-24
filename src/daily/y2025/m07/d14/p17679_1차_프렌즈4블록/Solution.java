package daily.y2025.m07.d14.p17679_1차_프렌즈4블록;

import java.util.*;

class Solution {

    static char[][] copyBoard;
    static int h, w;

    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.y, this.x);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node other = (Node) o;
            return this.y == other.y && this.x == other.x;
        }

    }

    static int checkNode() {
        Set<Node> removeNodeSet = new HashSet<>();

        for (int i = 0; i < h - 1; i++) {
            for (int j = 0; j < w - 1; j++) {
                if (copyBoard[i][j] != ' ') {
                    char ch1 = copyBoard[i][j];
                    char ch2 = copyBoard[i][j + 1];
                    char ch3 = copyBoard[i + 1][j];
                    char ch4 = copyBoard[i + 1][j + 1];

                    if (ch1 == ch2 && ch1 == ch3 && ch1 == ch4) {
                        removeNodeSet.add(new Node(i, j));
                        removeNodeSet.add(new Node(i, j + 1));
                        removeNodeSet.add(new Node(i + 1, j));
                        removeNodeSet.add(new Node(i + 1, j + 1));
                    }
                }

            }
        }

        for (Node node : removeNodeSet) {
            copyBoard[node.y][node.x] = ' ';
        }

        return removeNodeSet.size();
    }


    static void down() {
        for (int i = 0; i < w; i++) {
            Queue<Character> queue = new LinkedList<>();

            for (int j = 0; j < h; j++) {
                if (copyBoard[h - 1 - j][i] != ' ') {
                    queue.offer(copyBoard[h - j - 1][i]);
                }
            }

            int idx = h - 1;

            while (!queue.isEmpty()) {
                char ch = queue.poll();

                copyBoard[idx][i] = ch;
                idx--;
            }

            for (int j = idx; j >= 0; j--) {
                copyBoard[j][i] = ' ';
            }
        }
    }


    public int solution(int m, int n, String[] board) {
        int answer = 0;
        h = m;
        w = n;

        copyBoard = new char[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                copyBoard[i][j] = board[i].charAt(j);
            }
        }

        while (true) {
            int size = checkNode();

            if (size == 0) break;

            answer += size;
            down();
        }

        return answer;
    }
}