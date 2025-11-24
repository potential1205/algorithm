package daily.y2025.m09.d18.p92345_사라지는_발판;

import java.util.*;

class Solution {
    static int n, m;
    static Map<Long, Result> stateMap = new HashMap<>();
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Result {
        int moveCnt;
        boolean isWin;

        Result(int moveCnt, boolean isWin) {
            this.moveCnt = moveCnt;
            this.isWin = isWin;
        }
    }

    static long convert(long mask, int ay, int ax, int by, int bx, boolean turnA) {
        long nextState = mask;
        nextState |= ((long) ay << 25);
        nextState |= ((long) ax << 28);
        nextState |= ((long) by << 31);
        nextState |= ((long) bx << 34);
        if (turnA) nextState |= (1L << 37);
        return nextState;
    }

    static Result dfs(int depth, long mask, int ay, int ax, int by, int bx, boolean turnA) {
        long state = convert(mask, ay, ax, by, bx, turnA);

        // 이미 방문한 상태라면
        if (stateMap.containsKey(state)) {
            return stateMap.get(state);
        }

        // 누구 차례인지에 따라 사용할 좌표 결정
        int y = turnA ? ay : by;
        int x = turnA ? ax : bx;

        // 현재 위치에 발판이 없다면
        int idx = y * m + x;
        if ((mask & (1L << idx)) == 0) {
            Result result = new Result(depth, false);
            stateMap.put(state, result);
            return result;
        }

        // 현재칸 제거
        long nextMask = mask & ~(1L << idx);

        // 이동
        boolean canMove = false;
        int bestWinMoves = Integer.MAX_VALUE; // 이길 수 있으면 최소 이동 수로
        int bestLoseMoves = 0;                // 전부 지면 최대 이동 수로(최대한 버티기)

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

            int nidx = ny * m + nx;
            if ((nextMask & (1L << nidx)) == 0) continue;

            canMove = true;

            Result child = turnA ?
                    dfs(depth + 1, nextMask, ny, nx, by, bx, !turnA) :
                    dfs(depth + 1, nextMask, ay, ax, ny, nx, !turnA);

            // 자식이 지면(상대 패배) 나는 승리
            if (!child.isWin) {
                bestWinMoves = Math.min(bestWinMoves, child.moveCnt);
            } else {
                bestLoseMoves = Math.max(bestLoseMoves, child.moveCnt);
            }
        }

        Result result;
        if (!canMove) {
            result = new Result(depth, false);
        } else if (bestWinMoves != Integer.MAX_VALUE) {
            result = new Result(bestWinMoves, true);
        } else {
            result = new Result(bestLoseMoves, false);
        }

        stateMap.put(state, result);
        return result;
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;

        long mask = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    int bit = i * m + j;
                    mask |= (1L << bit);
                }
            }
        }

        Result result = dfs(0, mask, aloc[0], aloc[1], bloc[0], bloc[1], true);
        return result.moveCnt;
    }
}