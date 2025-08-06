package streak.d20250806.b1766_문제집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] inDegree = new int[n + 1];

		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			inDegree[to]++;
		}

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 1; i <= n; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			int cur = queue.remove();
			result.add(cur);

			for (int next : graph.get(cur)) {
				if (--inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}
}
