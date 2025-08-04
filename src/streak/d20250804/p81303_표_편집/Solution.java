package streak.d20250804.p81303_표_편집;

import java.util.*;

class Solution {
	static int[] pre;
	static int[] next;
	static int cur;

	static class Node {
		int num;
		int pre;
		int next;

		Node(int num, int pre, int next) {
			this.num = num;
			this.pre = pre;
			this.next = next;
		}
	}

	static void move(String type, int cnt) {
		if (type.equals("D")) {
			for (int i = 0; i < cnt; i++) {
				cur = next[cur];
			}
		} else if (type.equals("U")) {
			for (int i = 0; i < cnt; i++) {
				cur = pre[cur];
			}
		}
	}

	public String solution(int n, int start, String[] cmds) {
		pre = new int[n];
		next = new int[n];
		boolean[] active = new boolean[n];
		Arrays.fill(active, true);

		for (int i = 0; i < n; i++) {
			pre[i] = (i + n - 1) % n;
			next[i] = (i + 1) % n;
		}

		cur = start;

		ArrayDeque<Node> removeHistory = new ArrayDeque<>();

		for (String cmd : cmds) {
			String[] c = cmd.split(" ");

			if (c[0].equals("D")) {
				int cnt = Integer.parseInt(c[1]);
				move("D", cnt);
			} else if (c[0].equals("U")) {
				int cnt = Integer.parseInt(c[1]);
				move("U", cnt);
			} else if (c[0].equals("C")) {
				int preNum = pre[cur];
				int nextNum = next[cur];
				next[preNum] = nextNum;
				pre[nextNum] = preNum;
				removeHistory.offerLast(new Node(cur, pre[cur], next[cur]));
				active[cur] = false;
				if (cur > nextNum) { // 마지막행
					move("U", 1);
				} else {
					move("D", 1);
				}

			} else if (c[0].equals("Z")) {
				Node node = removeHistory.pollLast();
				next[node.pre] = node.num;
				pre[node.next] = node.num;
				active[node.num] = true;
			}
		}

		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < n; i++) {
			if (active[i]) {
				sb.append("O");
			} else {
				sb.append("X");
			}
		}

		return sb.toString();
	}
}