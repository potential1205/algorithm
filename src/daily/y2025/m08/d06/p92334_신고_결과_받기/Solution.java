package daily.y2025.m08.d06.p92334_신고_결과_받기;

import java.util.*;

class Solution {
	public int[] solution(String[] id_list, String[] reports, int k) {
		Map<String, Set<String>> to = new HashMap<>(); // 신고한 것
		Map<String, Set<String>> from = new HashMap<>(); // 신고받은 것
		Map<String, Integer> countMap = new LinkedHashMap<>();

		for (String name : id_list) {
			to.put(name, new HashSet<>());
			from.put(name, new HashSet<>());
			countMap.put(name, 0);
		}

		for (String report : reports) {
			String source = report.split(" ")[0];
			String target = report.split(" ")[1];

			Set<String> set1 = to.get(source);
			set1.add(target);
			to.put(source, set1);

			Set<String> set2 = from.get(target);
			set2.add(source);
			from.put(target, set2);
		}

		for (String str : from.keySet()) {
			Set<String> set = from.get(str);

			if (set.size() >= k) {
				for (String val : set) {
					countMap.put(val, countMap.get(val) + 1);
				}
			}
		}

		int[] answer = new int[id_list.length];
		int idx = 0;
		for (String name : countMap.keySet()) {
			answer[idx++] = countMap.get(name);
		}

		return answer;
	}
}