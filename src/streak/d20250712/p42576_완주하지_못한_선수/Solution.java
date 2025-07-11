package streak.d20250712.p42576_완주하지_못한_선수;

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < completion.length; i++) {
            if (map.containsKey(completion[i])) {
                map.put(completion[i], map.get(completion[i]) + 1);
            } else {
                map.put(completion[i], 1);
            }
        }

        for (int i = 0; i < participant.length; i++) {
            if (map.get(participant[i])  == null || map.get(participant[i]) == 0) {
                return participant[i];
            } else {
                map.put(participant[i], map.get(participant[i]) - 1);
            }
        }

        return "";
    }
}