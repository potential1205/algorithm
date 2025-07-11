package streak.d20250711.p1843_사칙연산;

class Solution {
    public int solution(String arr[]) {
        int answer = -1;

        int n = (arr.length + 1) / 2;
        int[] nums = new int[n];
        char[] opes = new char[n - 1];

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                nums[i / 2] = Integer.parseInt(arr[i]);
            } else {
                opes[i / 2] = arr[i].charAt(0);
            }
        }

        int[][] dpMax = new int[n][n];
        int[][] dpMin = new int[n][n];

        for (int i = 0; i < n; i++) {
            dpMax[i][i] = nums[i];
            dpMin[i][i] = nums[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                dpMin[i][j] = Integer.MAX_VALUE;
                dpMax[i][j] = Integer.MIN_VALUE;

                for (int k = i; k < j; k++) {
                    char ope = opes[k];

                    int leftMax  = dpMax[i][k];
                    int rightMax = dpMax[k+1][j];

                    int leftMin  = dpMin[i][k];
                    int rightMin = dpMin[k+1][j];

                    int[] cands = new int[4];
                    if (ope == '+') {
                        cands[0] = leftMax  + rightMax;
                        cands[1] = leftMax  + rightMin;
                        cands[2] = leftMin  + rightMax;
                        cands[3] = leftMin  + rightMin;
                    } else {
                        cands[0] = leftMax  - rightMax;
                        cands[1] = leftMax  - rightMin;
                        cands[2] = leftMin  - rightMax;
                        cands[3] = leftMin  - rightMin;
                    }

                    for (int v : cands) {
                        dpMax[i][j] = Math.max(dpMax[i][j], v);
                        dpMin[i][j] = Math.min(dpMin[i][j], v);
                    }
                }
            }
        }

        return dpMax[0][n - 1];
    }
}