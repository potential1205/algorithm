package streak.d20250828.p12905_가장_큰_정사각형_찾기;

class Timeout1 {

    static boolean isSquare(int[][] board, int sy, int sx, int size, int n, int m) {
        for (int i = sy; i < sy + size; i++) {
            for (int j = sx; j < sx + size; j++) {
                if (i >= n || j >= m) return false;
                if (board[i][j] == 0) return false;
            }
        }

        return true;
    }

    public int solution(int[][] board) {

        int n = board.length;
        int m = board[0].length;
        int cnt = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (n - i < cnt || m - j < cnt) break;

                if (board[i][j] == 0) {
                    continue;
                } else {
                    while (isSquare(board, i, j, cnt, n, m)) {
                        cnt++;
                    }
                }
            }
        }

        return (cnt - 1) * (cnt - 1);
    }
}