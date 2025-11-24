package daily.y2025.m09.d20.p72414_광고_삽입;

class Solution {

    static String toHMS(int sec) {
        int h = sec / 3600;
        sec %= 3600;
        int m = sec / 60;
        sec %= 60;
        int s = sec % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    static int toSec(String time) {
        String[] str = time.split(":");
        return Integer.valueOf(str[0]) * 60 * 60 + Integer.valueOf(str[1]) * 60 + Integer.valueOf(str[2]);
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int playStartTime = 0;
        int playEndTime = toSec(play_time);
        int advTime = toSec(adv_time);

        long[] cum = new long[playEndTime + 1];

        for (int i = 0; i < logs.length; i++) {
            String[] str = logs[i].split("-");
            int st = toSec(str[0]);
            int et = toSec(str[1]);
            cum[st]++;
            cum[et]--;
        }

        // 초당 동시 시청자 수
        for (int i = 1; i <= playEndTime; i++) {
            cum[i] += cum[i - 1];
        }

        // 누적 시청 시간
        for (int i = 1; i <= playEndTime; i++) {
            cum[i] += cum[i - 1];
        }

        long bestSum = cum[advTime - 1];
        int bestStart = 0;

        for (int start = 1; start + advTime - 1 <= playEndTime; start++) {
            int end = start + advTime - 1;
            long sum = cum[end] - cum[start - 1];
            if (sum > bestSum) {
                bestSum = sum;
                bestStart = start;
            }
        }

        return toHMS(bestStart);
    }
}