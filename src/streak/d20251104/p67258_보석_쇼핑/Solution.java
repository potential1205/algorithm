package streak.d20251104.p67258_보석_쇼핑;

import java.util.*;

class Solution {

    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        // 보석 개수 카운트를 위한 map 초기화
        for (String gem : gems) {
            map.put(gem, 0);
        }

        int left = 0;
        int right = 0;
        int gemTypeCount = map.size();

        int[] answer = new int[2];
        int minLen = 100000;
        int minLeft = 100000;

        // 첫번째 보석 추가
        set.add(gems[0]);
        map.put(gems[0], 1);

        while (true) {
            // 모든 종류의 보석이 아직 포함되지 있은 경우
            if (set.size() == gemTypeCount) {
                if (minLen > (right - left) || minLen == (right - left) && minLeft > left) {
                    minLen = right - left;
                    minLeft = left;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }

                map.put(gems[left], map.get(gems[left]) - 1);
                if (map.get(gems[left]) == 0) {
                    set.remove(gems[left]);
                }
                left++;

                // 모든 종류의 보석이 아직 포함되지 않은 경우
            } else {
                right++;
                if (right >= gems.length) break;
                map.put(gems[right], map.get(gems[right]) + 1);
                set.add(gems[right]);
            }
        }

        return answer;
    }
}