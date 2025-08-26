package streak.d20250826.p77886_110_옮기기;

import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] ans = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            StringBuilder stack = new StringBuilder();
            int count110 = 0;

            for (char c : str.toCharArray()) {
                stack.append(c);
                int n = stack.length();

                if (n >= 3 && stack.charAt(n - 3) == '1' && stack.charAt(n - 2) == '1' && stack.charAt(n - 1) == '0') {
                    stack.delete(n - 3, n);
                    count110++;
                }
            }

            // 삽입 위치 결정 : 남은 문자열 중 가장 뒤에 있는 0 찾기 (0이 없으면 즉, 모든 문자열이 1로만 이루어져 있으면 맨앞을 111 -> 110으로 변경하는게 이득)
            int targetIdx = 0;
            for (int j = stack.length() - 1; j >= 0; j--) {
                if (stack.charAt(j) == '0') {
                    targetIdx = j + 1;
                    break;
                }
            }

            // 대상 위치에 "110" 전부 삽입
            StringBuilder temp = new StringBuilder(stack.length() + 3 * count110);
            temp.append(stack, 0, targetIdx);
            for (int j = 0; j < count110; j++) temp.append("110");
            temp.append(stack, targetIdx, stack.length());

            ans[i] = temp.toString();
        }

        return ans;
    }
}