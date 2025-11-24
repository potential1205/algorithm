package daily.y2025.m07.d16.p72411_메뉴_리뉴얼;

import java.util.*;

class Solution {

    static void combination(char[] orderCharArr, int depthLimit, int depth, int start, String path,  Map<String, Integer> countMap) {
        if (depthLimit == depth) {
            countMap.put(path, countMap.getOrDefault(path, 0) + 1);
            return;
        }

        for (int i = start; i < orderCharArr.length; i++) {
            combination(orderCharArr, depthLimit, depth + 1, i + 1, path + orderCharArr[i], countMap);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for (int courseSize : course) {
            Map<String, Integer> countMap = new HashMap<>();

            for (String order : orders) {
                char[] orderCharArr = order.toCharArray();
                Arrays.sort(orderCharArr);
                combination(orderCharArr, courseSize, 0, 0, "", countMap);
            }

            int maxCount = 0;
            for (int cnt : countMap.values()) {
                if (cnt >= 2 && maxCount < cnt) {
                    maxCount = cnt;
                }
            }

            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                if (maxCount == entry.getValue()) {
                    answer.add(entry.getKey());
                }
            }
        }

        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }
}