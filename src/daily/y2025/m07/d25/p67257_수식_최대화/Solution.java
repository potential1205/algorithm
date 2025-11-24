package daily.y2025.m07.d25.p67257_수식_최대화;

import java.util.*;

class Solution {
	public long solution(String expression) {
		long answer = 0;
		String[] priorities = {"*+-", "+-*", "+*-", "-+*", "-*+", " *-+"};

		// 연산자, 피연산자 분리 및 저장
		List<String> list = new ArrayList<>();
		String temp = "";
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '*' || expression.charAt(i) == '+' || expression.charAt(i) == '-') {
				list.add(temp);
				temp = "";
				list.add("" + expression.charAt(i));
			} else {
				temp += ("" + expression.charAt(i));
			}
		}

		list.add(temp);

		ArrayDeque<String> deque1 = new ArrayDeque<>();
		ArrayDeque<String> deque2 = new ArrayDeque<>();


		// 우선순위에 따라 연산 해보기
		for (int i = 0; i < 6; i++) {

			for (int j= 0; j< list.size(); j++) {
				deque2.offerLast(list.get(j));
			}

			// 연산자 순서대로
			for (int j = 0; j < 3; j++) {
				while (!deque2.isEmpty()) {
					String ope = priorities[i].charAt(j) + "";
					String value = deque2.pollFirst();

					if (value.equals(ope)) {
						String v1 = deque1.pollLast();
						String v2 = deque2.pollFirst();

						if (ope.equals("+")) {
							String newValue = String.valueOf(Long.parseLong(v1) + Long.parseLong(v2));
							deque1.offerLast(newValue);
						} else if (ope.equals("-")) {
							String newValue = String.valueOf(Long.parseLong(v1) - Long.parseLong(v2));
							deque1.offerLast(newValue);
						} else if (ope.equals("*")) {
							String newValue = String.valueOf(Long.parseLong(v1) * Long.parseLong(v2));
							deque1.offerLast(newValue);
						}
					} else {
						deque1.offerLast(value);
					}
				}

				while (!deque1.isEmpty()) {
					String temp1 = deque1.pollFirst();
					deque2.offerLast(temp1);
				}
			}

			long result = Math.abs(Long.valueOf(deque2.poll()));
			answer = Math.max(answer, result);
		}

		return answer;
	}
}