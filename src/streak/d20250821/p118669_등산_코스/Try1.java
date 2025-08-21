package streak.d20250821.p118669_등산_코스;

import java.util.*;

class Try1 {

    static int[] p;
    static int[] size;
    static boolean[] isGate;
    static boolean[] isSummit;
    static boolean[] hasGate;
    static ArrayList<Integer>[] summitList;

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static void init(int n, int[] gates, int[] summits) {
        p = new int[n + 1];
        size = new int[n + 1];
        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];
        hasGate = new boolean[n + 1];
        summitList = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            p[i] = i;
            size[i] = 1;
        }

        for (int gate : gates) {
            isGate[gate] = true;
            hasGate[gate] = true;
        }

        for (int summit : summits) {
            isSummit[summit] = true;
        }

        for (int i = 1; i <= n; i++) {
            summitList[i] = new ArrayList<>();
            if (isSummit[i]) summitList[i].add(i);
        }
    }

    static int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }

        return p[x];
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        if (size[pa] >= size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
            hasGate[pa] = hasGate[pa] || hasGate[pb];

            summitList[pa].addAll(summitList[pb]);
            summitList[pb].clear();
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
            hasGate[pb] = hasGate[pa] || hasGate[pb];

            summitList[pb].addAll(summitList[pa]);
            summitList[pa].clear();
        }

        return true;
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int[] path : paths) {
            pq.offer(new Edge(path[0], path[1], path[2]));
        }

        int[] ans = new int[n+1];
        boolean[] decided = new boolean[n+1];

        init(n, gates, summits);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            int pa = find(edge.u);
            int pb = find(edge.v);

            if (pa == pb) continue;

            if (hasGate[pa] ^ hasGate[pb]) { // 둘 중 한쪽에만 게이트가 있다면
                int nonGate = hasGate[pa] ? pb : pa; // 게이트가 없는 쪽

                for (int summitNum : summitList[nonGate]) {
                    if (!decided[summitNum]) {
                        ans[summitNum] = edge.cost;
                        decided[summitNum] = true;
                    }
                }

                summitList[nonGate].clear();
            }

            union(pa, pb);

        }

        int bestSummit = -1;
        int bestInt = Integer.MAX_VALUE;
        Arrays.sort(summits); // 동률이면 번호 작은 것 우선
        for (int s : summits) {
            if (decided[s] && ans[s] < bestInt) {
                bestInt = ans[s];
                bestSummit = s;
            }
        }

        return new int[]{bestSummit, bestInt};
    }
}

// 아이디어 1 : 출입구부터 봉우리까지 단방향 탐색만 진행해도 충분함