package daily.y2025.m07.d24.p67256_키패드_누르기;

class Solution {
	public String solution(int[] numbers, String hand) {
		StringBuilder answer = new StringBuilder("");
		int curL = 10;
		int curR = 12;

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == 0) {
				numbers[i] = 11;
			}

			if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				answer.append("L");
				curL = numbers[i];
			}

			if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				answer.append("R");
				curR = numbers[i];
			}

			if (numbers[i] == 2 || numbers[i] == 5 || numbers[i] == 8 || numbers[i] == 11) {
				int targetY = (numbers[i] - 1) / 3;
				int targetX = (numbers[i] - 1) % 3;

				int leftY = (curL - 1) / 3;
				int leftX = (curL - 1) % 3;

				int rightY = (curR - 1) / 3;
				int rightX = (curR - 1) % 3;

				int gapL = Math.abs(targetY - leftY) + Math.abs(targetX - leftX);
				int gapR = Math.abs(targetY - rightY) + Math.abs(targetX - rightX);

				if (gapL == gapR && hand.equals("left")) {
					answer.append("L");
					curL = numbers[i];
				} else if (gapL == gapR && hand.equals("right")) {
					answer.append("R");
					curR = numbers[i];
				} else if (gapL < gapR) {
					answer.append("L");
					curL = numbers[i];
				} else if (gapL > gapR) {
					answer.append("R");
					curR = numbers[i];
				}
			}
		}

		return answer.toString();
	}
}