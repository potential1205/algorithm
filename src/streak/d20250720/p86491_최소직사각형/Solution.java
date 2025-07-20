package streak.d20250720.p86491_최소직사각형;

class Solution {
	public int solution(int[][] sizes) {
		int maxW = 0;
		int maxH = 0;

		for (int i = 0; i < sizes.length; i++) {
			int w = sizes[i][0];
			int h = sizes[i][1];

			if (w < h) {
				int temp = w;
				w = h;
				h = temp;
			}

			maxW = Math.max(maxW, w);
			maxH = Math.max(maxH, h);
		}

		return maxW * maxH;
	}
}