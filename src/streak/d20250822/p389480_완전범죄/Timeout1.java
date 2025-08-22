package streak.d20250822.p389480_완전범죄;

class Timeout1 {

    static int size;
    static int answerA = Integer.MAX_VALUE;
    static int answerB = Integer.MAX_VALUE;

    static void dfs(int depth, int[][] info, int n, int m, int a, int b) {
        if (answerA <= a) {
            return;
        }

        if (depth == size) {
            if (n > a && m > b) {
                answerA = Math.min(answerA, a);
                answerB = Math.min(answerB, b);
            }

            return;
        }

        if (n > a + info[depth][0]) {
            dfs(depth + 1, info, n, m, a + info[depth][0], b);
        }

        if (m > b + info[depth][1]) {
            dfs(depth + 1, info, n, m, a, b + info[depth][1]);
        }

    }

    public int solution(int[][] info, int n, int m) {
        size = info.length;

        dfs(0, info, n, m, 0, 0);

        if (answerA == Integer.MAX_VALUE) {
            answerA = -1;
        }

        return answerA;
    }
}
