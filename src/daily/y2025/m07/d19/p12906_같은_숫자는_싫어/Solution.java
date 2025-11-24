package daily.y2025.m07.d19.p12906_같은_숫자는_싫어;

import java.util.*;

public class Solution {
	public int[] solution(int []arr) {
		List<Integer> list = new ArrayList<>();

		int p = arr[0];
		list.add(p);

		for (int i = 0; i < arr.length - 1; i++) {
			if (p == arr[i + 1]) continue;

			p = arr[i + 1];
			list.add(p);
		}

		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}
}