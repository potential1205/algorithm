package streak.d20250720_H_Index;

import java.util.*;

class Solution2 {
	public int solution(int[] citations) {
		int answer = 0;

		Arrays.sort(citations);

		for (int i = 0; i < citations.length; i++) {
			int minVal = Math.min(citations[i], citations.length - i);
			answer = Math.max(answer, minVal);
		}

		return answer;
	}
}