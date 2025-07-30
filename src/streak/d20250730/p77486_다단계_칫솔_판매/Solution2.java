package streak.d20250730.p77486_다단계_칫솔_판매;

import java.util.*;

// 반복문 버전
class Solution2 {
	static Map<String, Integer> profit = new LinkedHashMap<>();
	static Map<String, String> parent = new LinkedHashMap<>();

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int n = enroll.length;
		int[] answer = new int[n];

		for (int i = 0; i < n; i++) {
			profit.put(enroll[i], 0);
			parent.put(enroll[i], referral[i]);
		}

		for (int i = 0; i < seller.length; i++) {
			String name = seller[i];
			int money = amount[i] * 100;

			while (!name.equals("-") && money > 0) {
				int upMoney = money / 10;
				int myMoney = money - upMoney;
				profit.put(name, profit.get(name) + myMoney);

				// 부모로 이동
				name = parent.get(name);
				money = upMoney;
			}
		}

		int idx = 0;
		for (String key : profit.keySet()) {
			answer[idx++] = profit.get(key);
		}

		return answer;
	}
}