package daily.y2025.m11.d05.p67258_보석_쇼핑;

import java.util.*;

class Solution {

    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (String gem : gems) {
            map.put(gem, 0);
        }

        int n = gems.length;
        int typeCnt = map.size();

        int left = 0;
        int right = 0;
        set.add(gems[0]);
        map.put(gems[0], 1);

        int[] answer = new int[2];
        int minLen = n;
        int minLeft = n;

        while (true) {
            if (set.size() == typeCnt) {
                int len = right - left;
                if (minLen > len || (minLen == len && minLeft > left)) {
                    minLen = len;
                    minLeft = left;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }

                map.put(gems[left], map.get(gems[left]) - 1);

                if (map.get(gems[left]) == 0) {
                    set.remove(gems[left]);
                }
                left++;
            } else {
                right++;
                if (right >= n) break;
                set.add(gems[right]);
                map.put(gems[right], map.get(gems[right]) + 1);
            }
        }

        return answer;
    }
}