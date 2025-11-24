package daily.y2025.m07.d24.p17682_다트_게임;

class Solution {
	public int solution(String dartResult) {
		int[] values = {0, 0, 0};
		int idx = -1;
		boolean flag = false;

		for (int i = 0; i < dartResult.length(); i++) {

			if (flag) {
				flag = false;
				continue;
			}

			char ch = dartResult.charAt(i);

			char ch2 = ' ';
			if ((i + 1) < dartResult.length()) {
				ch2 = dartResult.charAt(i + 1);
			}

			if (ch == '1' && ch2 == '0') {
				idx++;
				values[idx] = Integer.parseInt("10");
				flag = true;
			} else if ('0' <= ch && ch <= '9') {
				idx++;
				values[idx] = Integer.parseInt("" + ch);
			}

			if (ch == 'S') {
				values[idx] = (int) Math.pow(values[idx], 1);
			} else if (ch == 'D') {
				values[idx] = (int) Math.pow(values[idx], 2);
			} else if (ch == 'T') {
				values[idx] = (int) Math.pow(values[idx], 3);
			} else if (ch == '*') {
				if (idx == 0) {
					values[idx] *= 2;
				} else {
					values[idx] *= 2;
					values[idx - 1] *= 2;
				}
			} else if (ch == '#') {
				values[idx] *= -1;
			}
		}

		return values[0] + values[1] + values[2];
	}
}