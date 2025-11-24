package daily.y2025.m07.d16.p72411_메뉴_리뉴얼;

import java.util.*;

// 시간초과
class Try2 {

    static boolean[] visit;
    static List<String> candidate = new ArrayList<>();
    static List<Character> list = new ArrayList<>();
    static int listSize;

    static void combination(int depthLimit, int depth, int start, String path) {
        if (depthLimit == depth) {
            candidate.add(path);
            return;
        }

        for (int i = start; i < listSize; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            path += list.get(i);
            combination(depthLimit, depth + 1, i, path);
            path = path.substring(0, path.length() - 1);
            visit[i] = false;
        }

    }

    public String[] solution(String[] orders, int[] course) {

        Set<Character> set = new HashSet<>();

        for (String order : orders) {
            for (char ch : order.toCharArray()) {
                set.add(ch);
            }
        }

        for (char ch : set) {
            list.add(ch);
        }

        listSize = list.size();

        for (int depthLimit : course) {
            visit = new boolean[list.size()];
            combination(depthLimit, 0, 0, "");
        }

        Map<String, Integer> cntMap = new HashMap<>();

        for (String path : candidate) {
            cntMap.put(path, 0);
        }

        for (String path : candidate) {
            for (String order : orders) {
                int cnt = 0;

                for (int i = 0; i < path.length(); i++) {
                    if (order.indexOf(path.charAt(i)) == -1) {
                        continue;
                    } else {
                        cnt++;
                    }
                }

                if (cnt == path.length()) {
                    cntMap.put(path, cntMap.get(path) + 1);
                }
            }
        }

        List<String> answer = new ArrayList<>();

        for (int size : course) {
            int maxCnt = 0;

            for (String key : cntMap.keySet()) {
                if (size == key.length() && maxCnt < cntMap.get(key)) {
                    maxCnt = cntMap.get(key);
                }
            }

            for (String key : cntMap.keySet()) {
                if (size == key.length() && maxCnt >= 2 && maxCnt == cntMap.get(key)) {
                    answer.add(key);
                }
            }
        }

        Collections.sort(answer);

        String[] result = new String[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }
}