package daily.y2025.m08.d04.p72413_합승_택시_요금;

import java.util.*;

class Solution {

	static List<List<Node>> graph;
	static int[][] arr;

	static class Node implements Comparable<Node> {
		int num;
		int cost;

		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static void dijkstra(int start, int[][] fares, int n) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		boolean[] visit = new boolean[n + 1];

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (visit[cur.num]) continue;
			visit[cur.num] = true;

			for (Node next : graph.get(cur.num)) {
				if (dist[next.num] > dist[cur.num] + next.cost) {
					dist[next.num] = dist[cur.num] + next.cost;
					pq.offer(new Node(next.num, dist[next.num]));
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			arr[start][i] = dist[i];
		}
	}

	public int solution(int n, int s, int a, int b, int[][] fares) {
		arr = new int[n + 1][n + 1];

		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < fares.length; i++) {
			int from = fares[i][0];
			int to = fares[i][1];
			int cost = fares[i][2];
			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}

		for (int i = 1; i <= n; i++) {
			dijkstra(i, fares, n);
		}

		int answer = arr[s][a] + arr[s][b];

		for (int i = 1; i <= n; i++) {
			int val = arr[s][i] + arr[i][a] + arr[i][b];
			answer = Math.min(answer, val);
		}

		return answer;
	}
}