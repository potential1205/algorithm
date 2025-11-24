package daily.y2025.m08.d08.p42892_길_찾기_게임;

import java.util.*;

class Solution {
	static class Node implements Comparable<Node> {
		int id;
		int x;
		int y;
		Node left;
		Node right;

		Node(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			int gap = o.y - this.y;

			if (gap == 0) {
				return this.x - o.x;
			}

			return o.y - this.y;
		}
	}

	static void insert(Node cur, Node child) {
		if (child.x < cur.x) {
			if (cur.left == null) {
				cur.left = child;
			} else {
				insert(cur.left, child);
			}
		} else {
			if (cur.right == null) {
				cur.right = child;
			} else {
				insert(cur.right, child);
			}
		}
	}

	static void preOrder(Node cur, List<Node> path) {
		path.add(cur);

		if (cur.left != null) {
			preOrder(cur.left, path);
		}

		if (cur.right != null) {
			preOrder(cur.right, path);
		}
	}

	static void postOrder(Node cur, List<Node> path) {
		if (cur.left != null) {
			postOrder(cur.left, path);
		}

		if (cur.right != null) {
			postOrder(cur.right, path);
		}

		path.add(cur);
	}

	public int[][] solution(int[][] nodeinfo) {
		int n = nodeinfo.length;
		List<Node> graph = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			int[] info = nodeinfo[i];
			graph.add(new Node(i + 1, info[0], info[1]));
		}

		Collections.sort(graph);

		Node root = graph.get(0);
		for (int i = 1; i < n; i++) {
			insert(root, graph.get(i));
		}

		List<Node> pre = new ArrayList<>();
		List<Node> post = new ArrayList<>();

		preOrder(root, pre);
		postOrder(root, post);

		int[][] answer = new int[2][n];

		for (int i = 0; i < n; i++) {
			answer[0][i] = pre.get(i).id;
		}

		for (int i = 0; i < n; i++) {
			answer[1][i] = post.get(i).id;
		}

		return answer;
	}
}