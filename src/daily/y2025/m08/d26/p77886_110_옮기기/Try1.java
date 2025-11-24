package daily.y2025.m08.d26.p77886_110_옮기기;

import java.util.*;

class Try1 {

    static int find(String str, int len, int start) {
        for (int i = start; i < len - 2; i++) {
            if (!(str.charAt(i) == '1' && str.charAt(i + 1) == '1' && str.charAt(i + 2) == '0') &&
                    !(str.charAt(i) == '1' && str.charAt(i + 1) == '1' && str.charAt(i + 2) == '1')) {
                return i;
            }
        }

        return -1;
    }

    static int find111(String str, int len, int end) {
        for (int i = 0; i < end; i++) {
            if (str.charAt(i) == '1' && str.charAt(i + 1) == '1' && str.charAt(i + 2) == '1') {
                return i;
            }
        }

        return -1;
    }

    static int find110(String str, int len) {
        for (int i = 0; i < len - 2; i++) {
            if (str.charAt(i) == '1' && str.charAt(i + 1) == '1' && str.charAt(i + 2) == '0') {
                return i;
            }
        }

        return -1;
    }

    static String swap(String str, int idx1, int idx2) {
        // if (idx1 + 2 >= str.length() || idx2 + 2 >= str.length()) {
        //     return "";
        // }

        String part1 = str.substring(idx1, idx1 + 3);
        String part2 = str.substring(idx2, idx2 + 3);

        StringBuilder sb = new StringBuilder(str);
        sb.replace(idx1, idx1 + 3, part2);
        sb.replace(idx2, idx2 + 3, part1);

        return sb.toString();
    }

    public String[] solution(String[] s) {
        List<String> answer = new ArrayList<>();

        for (String str : s) {
            int len = str.length();

            while (true) {
                int idx1 = find110(str, len);

                if (idx1 == -1) {
                    break;
                }

                int idx2 = find111(str, len, idx1);

                if (idx2 != -1) { // 111과 110 교환
                    str = swap(str, idx1, idx2);
                    continue;
                }

                int idx3 = find(str, len, idx1 + 3);

                if (idx3 != -1) { // 문자열 교환
                    str = swap(str, idx1, idx3);
                    continue;
                }

                answer.add(str);
                break;
            }
        }

        System.out.println(answer);

        return new String[3];
    }
}