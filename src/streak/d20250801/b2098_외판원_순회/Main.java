package streak.d20250801.b2098_외판원_순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] dist;
	static int[][] arr;
	static List<List<Node>> graph;

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

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			for (Node next : graph.get(cur.num)) {
				int newCost = dist[cur.num] + next.cost;
				if (dist[next.num] > newCost) {
					dist[next.num] = newCost;
					pq.offer(new Node(next.num, newCost));
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (i != start) {
				arr[start][i] = dist[i];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		arr = new int[n][n];

		graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				int cost = Integer.parseInt(st.nextToken());
				graph.get(i).add(new Node(j, cost));
			}
		}

		for (int i = 0; i < n; i++) {
			dist = new int[n];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dijkstra(i);
		}

		// dp[방문한집합][현재위치]
		int size = (int) Math.pow(2, n);
		int[][] dp = new int[size][n];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}

		dp[0][0] = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) == 0) continue;
				int curValue = dp[i][j];
				if (dp[i][j] == Integer.MAX_VALUE) continue;

				for (int next = 0; next < n; next++) {
					if ((i & (1 << next)) != 0) continue;
					int nextValue = i | (1 << next);
					dp[nextValue][next] = Math.min(dp[nextValue][next], curValue + arr[j][next]);
				}
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println(dp[i][j]);
			}
		}
	}
}
