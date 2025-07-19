package streak.d20250719.p42748_K번째수;

import java.util.*;

class Solution {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		int idx = 0;

		for (int[] command : commands) {
			PriorityQueue<Integer> pq = new PriorityQueue<>();

			for (int i = command[0] - 1; i < command[1]; i++) {
				pq.offer(array[i]);
			}

			int val = 0;

			for (int i = 0; i < command[2]; i++) {
				val = pq.poll();
			}

			answer[idx++] = val;
		}

		return answer;
	}
}