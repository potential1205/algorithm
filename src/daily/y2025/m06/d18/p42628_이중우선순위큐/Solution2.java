package daily.y2025.m06.d18.p42628_이중우선순위큐;

import java.util.*;

class Solution2 {

    static void deleteValue(TreeMap<Integer, Integer> map, int key) {
        if (!map.isEmpty()) {
            if (map.get(key) == 1) {
                map.remove(key);
            } else {
                map.put(key, map.get(key) - 1);
            }
        }
    }

    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (String ope : operations) {
            String[] info = ope.split(" ");

            if (info[0].equals("I")) {
                int val = Integer.parseInt(info[1]);
                map.put(val, map.getOrDefault(val, 0) + 1);

            } else if (info[0].equals("D") && info[1].equals("-1")) {
                if (!map.isEmpty()) {
                    deleteValue(map, map.firstKey());
                }
            } else if (info[0].equals("D") && info[1].equals("1")) {
                if (!map.isEmpty()) {
                    deleteValue(map, map.lastKey());
                }
            }
        }

        int[] answer = new int[2];

        if (map.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = map.lastKey();
            answer[1] = map.firstKey();
        }

        return answer;
    }
}