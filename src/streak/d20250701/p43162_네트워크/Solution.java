package streak.d20250701.p43162_네트워크;

class Solution {

    static int id = 0;
    static int[] visit;

    static void dfs(int num, int[][] computers, int n) {
        if (visit[num] > 0) {
            return;
        }

        visit[num] = id;

        for (int i = 0; i < n; i++) {
            if (computers[num][i] == 1 && num != i) {
                dfs(i, computers, n);
            }
        }
    }

    public int solution(int n, int[][] computers) {
        visit = new int[n];

        for (int i = 0; i < n; i++) {
            if (visit[i] == 0) {
                id++;
                dfs(i, computers, n);
            }
        }

        return id;
    }
}