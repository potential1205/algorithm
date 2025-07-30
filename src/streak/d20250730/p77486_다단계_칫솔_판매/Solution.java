package streak.d20250730.p77486_다단계_칫솔_판매;

import java.util.*;

class Solution {

	static Map<String, Integer> profit = new LinkedHashMap<>();
	static Map<String, String> parent = new LinkedHashMap<>();

	static void dfs(String name, int money) {

		if (parent.get(name).equals("-")) {

			int upMoney = (int) (money * 0.1);

			if (upMoney < 1) {
				profit.put(name, profit.get(name) + money);
			} else {
				profit.put(name, profit.get(name) + money - upMoney);
			}
			return;
		}

		int upMoney =  (int) (money * 0.1);

		if (upMoney < 1) {
			profit.put(name, profit.get(name) + money);
		} else {
			profit.put(name, profit.get(name) + money - upMoney);
			dfs(parent.get(name), upMoney);
		}
	}

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int n = enroll.length;
		int[] answer = new int[n];

		profit.put("-", 0);
		for (int i = 0; i < enroll.length; i++) {
			profit.put(enroll[i], 0);
		}

		for (int i = 0; i < referral.length; i++) {
			parent.put(enroll[i], referral[i]);
		}

		for (int i = 0; i < seller.length; i++) {
			String name = seller[i];
			int money = amount[i] * 100;
			dfs(name, money);
		}

		int idx = 0;
		for (String key : profit.keySet()) {
			if (!key.equals("-")) {
				answer[idx++] = profit.get(key);
			}
		}

		return answer;
	}
}