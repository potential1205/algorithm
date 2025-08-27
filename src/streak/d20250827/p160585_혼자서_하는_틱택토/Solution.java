package streak.d20250827.p160585_혼자서_하는_틱택토;

class Solution {

    static boolean isWin(String[] board, char target) {

        // 가로
        if (board[0].charAt(0) == target && board[0].charAt(1) == target && board[0].charAt(2) == target) {
            return true;
        }

        if (board[1].charAt(0) == target && board[1].charAt(1) == target && board[1].charAt(2) == target) {
            return true;
        }

        if (board[2].charAt(0) == target && board[2].charAt(1) == target && board[2].charAt(2) == target) {
            return true;
        }

        // 세로
        if (board[0].charAt(0) == target && board[1].charAt(0) == target && board[2].charAt(0) == target) {
            return true;
        }

        if (board[0].charAt(1) == target && board[1].charAt(1) == target && board[2].charAt(1) == target) {
            return true;
        }

        if (board[0].charAt(2) == target && board[1].charAt(2) == target && board[2].charAt(2) == target) {
            return true;
        }

        // 대각선
        if (board[0].charAt(0) == target && board[1].charAt(1) == target && board[2].charAt(2) == target) {
            return true;
        }

        if (board[0].charAt(2) == target && board[1].charAt(1) == target && board[2].charAt(0) == target) {
            return true;
        }

        return false;
    }

    public int solution(String[] board) {
        int oCnt = 0;
        int xCnt = 0;

        for (int i = 0; i < 3; i++) {
            String line = board[i];
            for (int j = 0; j < 3; j++) {
                char ch = line.charAt(j);
                if (ch == 'O') {
                    oCnt++;
                }

                if (ch == 'X') {
                    xCnt++;
                }
            }
        }

        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');

        // x가 더 많은 경우
        if (xCnt > oCnt) {
            return 0;
        }

        // o가 x보다 2개 이상 많은 경우
        if (oCnt > (xCnt + 1)) {
            return 0;
        }

        // 둘 다 이긴 경우
        if (oWin && xWin) {
            return 0;
        }

        // o가 이겼는데 o가 x와 1개 차이가 아니라면
        if (oWin && (oCnt != (xCnt + 1))) {
            return 0;
        }

        // x가 이겼는데 두 돌의 개수가 다른 경우
        if (xWin && xCnt != oCnt) {
            return 0;
        }

        return 1;
    }
}