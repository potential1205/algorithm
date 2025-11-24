package daily.y2025.m07.d02.p42842_카펫;

class Solution {

    public int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};

        for (int w = 1; w <= 5000; w++) {
            for (int h = 1; h <= w; h++) {
                if (w * h == yellow && (w * 2) + (h * 2) + 4 == brown) {
                    answer[0] = w + 2;
                    answer[1] = h + 2;
                    return answer;
                }
            }
        }

        return answer;
    }
}