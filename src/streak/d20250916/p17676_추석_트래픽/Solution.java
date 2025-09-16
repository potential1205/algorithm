package streak.d20250916.p17676_추석_트래픽;

class Solution {

    static int convert(String line) {
        int hh = Integer.parseInt("" + line.charAt(11) + line.charAt(12)) * 60 * 60 * 1000;
        int mm = Integer.parseInt("" + line.charAt(14) + line.charAt(15)) * 60 * 1000;
        int ss = Integer.parseInt("" + line.charAt(17) + line.charAt(18)) * 1000;
        int sss = Integer.parseInt("" + line.charAt(20) + line.charAt(21) + line.charAt(22));
        return hh + mm + ss + sss;
    }

    static int convertStartTime(String line, int endTime) {
        String[] strArr = line.split(" ");
        String str = strArr[2];
        str = str.substring(0, str.length() - 1);
        double val = Double.parseDouble(str);
        int a = (int) val;
        int b = (int) ((val - a) * 1000);
        return endTime - (a * 1000 + b) + 1;
    }

    public int solution(String[] lines) {
        int answer = 0;
        int n = lines.length;
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            endTimes[i] = convert(lines[i]);
            startTimes[i] = convertStartTime(lines[i], endTimes[i]);
        }

        // 시작점 기준
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            int left = startTimes[i];
            int right = left + 999;

            for (int j = 0; j < n; j++) {
                if (startTimes[j] <= right && left <= endTimes[j]) {
                    cnt++;
                }
            }

            answer = Math.max(answer, cnt);
        }

        // 끝점 기준
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            int left = endTimes[i];
            int right = left + 999;

            for (int j = 0; j < n; j++) {
                if (startTimes[j] <= right && left <= endTimes[j]) {
                    cnt++;
                }
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }
}