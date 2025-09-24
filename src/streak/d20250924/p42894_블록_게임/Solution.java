package streak.d20250924.p42894_블록_게임;

import java.util.*;

class Solution {

    static boolean check(int[][] board, int y, int x) {
        for (int i = 0; i < y; i++) {
            if (board[i][x] != 0) return false;
        }

        return true;
    }

    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;

        while (true) {
            boolean remove = false;
            boolean[][] visit = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) continue;
                    if (visit[i][j]) continue;

                    // 박스 범위 구하기
                    int minY = i;
                    int maxY = i;
                    int minX = j;
                    int maxX = j;
                    int count = 0;

                    for (int a = 0; a < n; a++) {
                        for (int b = 0; b < n; b++) {
                            if (board[i][j] == board[a][b]) {
                                count++;
                                visit[a][b] = true;
                                minY = Math.min(minY, a);
                                maxY = Math.max(maxY, a);
                                minX = Math.min(minX, b);
                                maxX = Math.max(maxX, b);
                            }
                        }
                    }

                    List<int[]> blocks = new ArrayList<>();
                    List<int[]> holes = new ArrayList<>();

                    for (int a = minY; a <= maxY; a++) {
                        for (int b = minX; b <= maxX; b++) {
                            if (board[a][b] == 0) {
                                holes.add(new int[]{a, b});
                            } else if (board[a][b] == board[i][j]) {
                                blocks.add(new int[]{a, b});
                            }
                        }
                    }

                    if (holes.size() != 2) continue;

                    // hole 위에 블럭이 있는지
                    boolean isRemovable = true;
                    for (int[] hole : holes) {
                        int a = hole[0];
                        int b = hole[1];

                        if (!check(board, a, b)) {
                            isRemovable = false;
                            break;
                        }
                    }

                    if (!isRemovable) continue;

                    for (int[] block : blocks) {
                        board[block[0]][block[1]] = 0;
                    }

                    remove = true;
                    answer++;
                }
            }

            if (!remove) {
                break;
            }
        }

        return answer;
    }
}