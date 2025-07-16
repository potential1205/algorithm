package streak.d20250716.p72411_메뉴_리뉴얼;

import java.util.*;

class Try1 {

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

        // 1. 원소 구하기
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
            System.out.println(path);
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

        for (String key : cntMap.keySet()) {
            if (cntMap.get(key) >= 2) {
                System.out.println(key + " " + cntMap.get(key));
            }
        }

        return new String[5];
    }
}