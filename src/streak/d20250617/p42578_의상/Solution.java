package streak.d20250617.p42578_의상;

import java.util.*;

class Solution {

    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, List<String>> clothMap = new HashMap<>();

        for (String[] info : clothes) {
            if (clothMap.containsKey(info[1])) {
                List<String> list = clothMap.get(info[1]);
                list.add(info[0]);
                clothMap.put(info[1], list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(info[0]);
                clothMap.put(info[1], list);
            }
        }

        for (String key : clothMap.keySet()) {
            List<String> list = clothMap.get(key);
            answer *= (list.size() + 1);
        }

        return answer-1;
    }
}