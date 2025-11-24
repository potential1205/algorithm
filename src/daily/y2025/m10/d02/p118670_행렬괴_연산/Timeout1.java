package daily.y2025.m10.d02.p118670_행렬괴_연산;

class Timeout1 {
    public int[][] solution(int[][] rc, String[] operations) {
        int[][] answer = {};

        int n = rc.length;
        int m = rc[0].length;

        for (String ope : operations) {
            if (ope.equals("Rotate")) {

                // 오른쪽
                int temp1 = rc[0][m - 1];
                for (int i = m - 1; i >= 1; i--) {
                    rc[0][i] = rc[0][i - 1];
                }

                // 아래
                int temp2 = rc[n - 1][m - 1];
                for (int i = n - 1; i >= 1; i--) {
                    rc[i][m - 1] = rc[i - 1][m - 1];
                }
                rc[1][m - 1] = temp1;

                // 왼쪽
                int temp3 = rc[n - 1][0];
                for (int i = 0; i < n - 1; i++) {
                    rc[n - 1][i] = rc[n - 1][i + 1];
                }
                rc[n - 1][m - 2] = temp2;

                // 위쪽
                int temp4 = rc[0][0];
                for (int i = 0; i < n - 1; i++) {
                    rc[i][0] = rc[i + 1][0];
                }
                rc[n - 2][0] = temp3;


            } else if (ope.equals("ShiftRow")) {
                int[] lastLine = rc[n - 1];

                for (int i = n - 1; i >= 1; i--) {
                    rc[i] = rc[i - 1];
                }

                rc[0] = lastLine;
            }
        }

        return rc;
    }
}