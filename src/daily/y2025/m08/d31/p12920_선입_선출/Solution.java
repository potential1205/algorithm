package daily.y2025.m08.d31.p12920_선입_선출;

class Solution {

    public int solution(int n, int[] cores) {
        int coresLen = cores.length;
        int left = 0;
        int right = 50000;
        int time = 50000;

        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = 0;
            for (int i = 0; i < coresLen; i++) {
                cnt += mid / cores[i];
            }

            if (cnt >= (n - coresLen)) {
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }


        int answer = 0;

        int beforeTimeCnt = coresLen;
        for (int i = 0; i < coresLen; i++) {
            beforeTimeCnt += ((time - 1) / cores[i]);
        }

        int m = n - beforeTimeCnt;

        for (int i = 0; i < coresLen; i++) {
            if (time % cores[i] == 0) {
                m--;

                if (m == 0) return i + 1;
            }
        }

        return -1;
    }
}