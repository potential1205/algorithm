package streak.d20250718.p77485_행렬_테두리_회전하기;

import java.util.*;

class Solution {

    public int[] solution(int rows, int columns, int[][] queries) {

        int val = 1;
        int[][] arr = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = val++;
            }
        }

        int[] answer = new int[queries.length];
        int idx = 0;

        for (int[] query : queries) {
            int sy = query[0] - 1;
            int sx = query[1] - 1;
            int ey = query[2] - 1;
            int ex = query[3] - 1;

            int initVal = arr[sy][sx];
            int minVal = initVal;

            for (int i = sy;  i < ey; i++) {
                arr[i][sx] = arr[i + 1][sx];
                minVal = Math.min(minVal, arr[i][sx]);
            }

            for (int i = sx;  i < ex; i++) {
                arr[ey][i] = arr[ey][i + 1];
                minVal = Math.min(minVal, arr[ey][i]);
            }

            for (int i = ey;  i > sy; i--) {
                arr[i][ex] = arr[i - 1][ex];
                minVal = Math.min(minVal, arr[i][ex]);
            }

            for (int i = ex;  i > sx; i--) {
                arr[sy][i] = arr[sy][i - 1];
                minVal = Math.min(minVal, arr[sy][i]);
            }

            arr[sy][sx + 1] = initVal;
            answer[idx++] = minVal;
        }

        return answer;
    }
}