package daily.y2025.m07.d22.p150370_개인정보_수집_유효기간;

import java.util.*;

class Solution {

	static int dateToNum(String date) {
		String[] ymd = date.split("\\.");

		return Integer.parseInt(ymd[0]) * 28 * 12 +
			Integer.parseInt(ymd[1]) * 28 +
			Integer.parseInt(ymd[2]);
	}

	public int[] solution(String today, String[] terms, String[] privacies) {
		List<Integer> list = new ArrayList<>();
		int todayNum = dateToNum(today);

		Map<String, Integer> map = new HashMap<>();
		for (String term : terms) {
			String[] termSplit = term.split(" ");
			map.put(termSplit[0], Integer.parseInt(termSplit[1]));
		}

		for (int i = 0; i < privacies.length; i++) {
			String[] privacy = privacies[i].split(" ");
			int targetDayNum = dateToNum(privacy[0]);
			targetDayNum += (map.get(privacy[1]) * 28);

			if (targetDayNum - todayNum <= 0) {
				list.add(i + 1);
			}
		}

		return list.stream().mapToInt(val -> val).toArray();
	}
}