package streak.d202508011.p389478_택배_상자_꺼내기;

class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int h = n / w;
        int[][] arr = new int[h + 1][w];

        int targetY = 0;
        int targetX = 0;

        for (int i = 1; i <= n; i++) {
            int y = (i - 1) / w;
            int x;

            if (y % 2 == 0) {
                x = (i - 1) % w;
            } else {
                x = (w - 1) - (i - 1) % w;
            }

            arr[y][x] = i;

            if (i == num) {
                targetY = y;
                targetX = x;
            }
        }

        while (targetY <= h && arr[targetY][targetX] != 0) {
            answer++;
            targetY++;
        }

        return answer;
    }
}