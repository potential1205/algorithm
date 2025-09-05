package streak.d20250905.p258711_도넛과_막대_그래프;

import java.util.*;

// 엣지케이스에 걸림
class Try1 {
    public int[] solution(int[][] edges) {
        int n = 0;
        for (int[] edge : edges) {
            n = Math.max(n, edge[0]);
            n = Math.max(n, edge[1]);
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            in[to]++;
            out[from]++;
        }

        // 진입차수가 0이고 진출차수가 2이상인 경우가 출발점
        int start = 0;
        int maxOut = 0;
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                start = i;
            }
        }

        int graphCnt = out[start];

        // 시작 정점을 제외한 그래프 다시 그리기
        in = new int[n + 1];
        out = new int[n + 1];
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (from == start) continue;

            in[to]++;
            out[from]++;
            graph.get(from).add(to);
        }

        int answer1 = 0; // 도넛
        int answer2 = 0; // 막대
        int answer3 = 0; // 팔자

        boolean[] visit = new boolean[n + 1];

        // 막대 그래프 시작점은 진입차수가 0
        for (int i = 1; i <= n; i++) {
            if (visit[i] || i == start) continue;

            if (in[i] == 0) {
                //System.out.println("막대 시작 : " + i);
                ArrayDeque<Integer> queue = new ArrayDeque<>();
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    visit[cur] = true;

                    for (int next : graph.get(cur)) {
                        queue.offer(next);
                    }
                }

                answer2++;
            }
        }

        // 8자 그래프 시작점은 진출차수가 2
        for (int i = 1; i <= n; i++) {
            if (visit[i] || i == start) continue;

            if (out[i] == 2) {
                //System.out.println("8자 시작 : " + i);
                ArrayDeque<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                visit[i] = true;

                while (!queue.isEmpty()) {
                    int cur = queue.poll();

                    for (int next : graph.get(cur)) {
                        if (!visit[next]) {
                            queue.offer(next);
                            visit[next] = true;
                        }
                    }
                }

                answer3++;
            }
        }

        // 남은 애들돌면 도넛
        for (int i = 1; i <= n; i++) {
            if (visit[i] || i == start) continue;

            //System.out.println("도넛 시작 : " + i);
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            visit[i] = true;

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int next : graph.get(cur)) {
                    if (!visit[next]) {
                        queue.offer(next);
                        visit[next] = true;
                    }
                }
            }

            answer1++;
        }

        System.out.println(graphCnt);
        int[] answer = new int[4];
        answer[0] = start;
        answer[1] = answer1;
        answer[2] = answer2;
        answer[3] = answer3;
        return answer;
    }
}