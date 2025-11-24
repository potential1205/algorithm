package daily.y2025.m09.d05.p258711_도넛과_막대_그래프;

class Solution {
    public int[] solution(int[][] edges) {
        int n = 0;
        for (int[] edge : edges) {
            n = Math.max(n, edge[0]);
            n = Math.max(n, edge[1]);
        }

        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        boolean[] active = new boolean[n + 1];

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            in[to]++;
            out[from]++;
            active[to] = true;
            active[from] = true;
        }

        // 진입차수가 0이고 진출차수가 2이상인 경우가 출발점
        int start = 0;
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                start = i;
            }
        }

        int graphCnt = out[start];

        // 시작 정점을 제외한 진출, 진입 차수 다시 계산
        in = new int[n + 1];
        out = new int[n + 1];

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (from == start) continue;

            in[to]++;
            out[from]++;
        }

        int answer1 = 0; // 도넛
        int answer2 = 0; // 막대
        int answer3 = 0; // 팔자

        for (int i = 1; i <= n; i++) {
            if (i == start) continue;
            if (!active[i]) continue;

            if (in[i] == 0) {
                answer2++;
            }

            if (out[i] == 2 && in[i] == 2) {
                answer3++;
            }
        }

        int[] answer = new int[4];
        answer[0] = start;
        answer[1] = graphCnt - answer2 - answer3;
        answer[2] = answer2;
        answer[3] = answer3;
        return answer;
    }
}