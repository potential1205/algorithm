package daily.y2025.m07.d23.p87694_아이템_줍기;

import java.util.*;

class Solution {

	static Set<Node> borderSet = new HashSet<>();

	static class Node {
		int y;
		int x;
		int cnt;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		Node(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null) return false;
			if (!(o instanceof Node)) return false;

			Node other = (Node) o;
			return this.y == other.y && this.x == other.x;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.y, this.x);
		}
	}

	static void addRectangle(int[] rectangle) {
		for (int i = rectangle[1] * 2; i <= rectangle[3] * 2; i++) {
			for (int j = rectangle[0] * 2; j <= rectangle[2] * 2; j++) {
				borderSet.add(new Node(i, j));
			}
		}
	}

	static int bfs(int sy, int sx, int ty, int tx) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		List<Node> borderList = new ArrayList<>(borderSet);
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(sy, sx, 0));

		boolean[][] visit = new boolean[51 * 2][51 * 2];
		visit[sy][sx] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (cur.y == ty && cur.x == tx) {
				return cur.cnt / 2;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (!visit[ny][nx] && borderList.contains(new Node(ny, nx))) {
					queue.offer(new Node(ny, nx, cur.cnt + 1));
					visit[ny][nx] = true;
				}
			}
		}

		return 0;
	}

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		for (int i = 0; i < rectangle.length; i++) {
			addRectangle(rectangle[i]);
		}

		Set<Node> removeSet = new HashSet<>();

		for (Node node : borderSet) {
			for (int i = 0; i < rectangle.length; i++) {
				if (2 * rectangle[i][1] < node. y && node.y < 2 * rectangle[i][3] && 2 * rectangle[i][0] < node. x && node.x < 2 * rectangle[i][2]) {
					removeSet.add(new Node(node.y, node.x));
				}
			}
		}

		borderSet.removeAll(removeSet);

		return bfs(2 * characterY, 2 * characterX, 2 * itemY, 2 * itemX);
	}
}