package streak.d20250804.p81303_표_편집;

import java.util.*;

class Try1 {
	public String solution(int n, int cursor, String[] cmds) {
		boolean[] isActive = new boolean[n];
		Arrays.fill(isActive, true);

		ArrayDeque<Integer> removeHistory = new ArrayDeque<>();

		for (String cmd : cmds) {
			String[] c = cmd.split(" ");

			if (c[0].equals("D")) {
				int cnt = 0;
				int target = Integer.parseInt(c[1]);
				while (cnt < target) {
					cursor = (cursor + 1) % n;
					if (isActive[cursor]) {
						cnt++;
					}

				}

			} else if (c[0].equals("U")) {
				int cnt = 0;
				int target = Integer.parseInt(c[1]);
				while (cnt < target) {
					cursor = (cursor + n - 1) % n;
					if (isActive[cursor]) {
						cnt++;
					}

				}
			} else if (c[0].equals("C")) {
				isActive[cursor] = false;
				removeHistory.offerLast(cursor);

				if (cursor == (n - 1)) {
					cursor = n - 2;
				} else {
					while (!isActive[cursor]) {
						cursor = (cursor + 1) % n;
					}
				}

			} else if (c[0].equals("Z")) {
				int index = removeHistory.pollLast();
				isActive[index] = true;
			}

			// System.out.print(cursor + " : ");
			// for (int i = 0; i < n; i++) {
			//     System.out.print(isActive[i] + " ");
			// }
			// System.out.println();
		}


		// 출력
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < n; i++) {
			if (isActive[i]) {
				sb.append("O");
			} else {
				sb.append("X");
			}
		}

		return sb.toString();
	}
}