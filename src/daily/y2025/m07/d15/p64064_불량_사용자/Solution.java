package daily.y2025.m07.d15.p64064_불량_사용자;

import java.util.*;

class Solution {

    static boolean[] visit;
    static Set<String> answer = new HashSet<>();

    static void combination(int depth, List<Integer>[] cnts, List<Integer> path) {
        if (depth == cnts.length) {
            Collections.sort(path);

            String str = "";
            for (int val : path) {
                str += String.valueOf(val);
            }

            answer.add(str);
            return;
        }

        for (int val : cnts[depth]) {
            if (visit[val]) continue;

            visit[val] = true;
            path.add(val);
            combination(depth + 1, cnts, path);
            path.remove(Integer.valueOf(val));
            visit[val] = false;
        }

    }

    public int solution(String[] user_id, String[] banned_id) {

        List<Integer>[] cnts = new ArrayList[banned_id.length];
        for (int i = 0; i < banned_id.length; i++) {
            cnts[i] = new ArrayList<>();
        }

        for (int i = 0; i < banned_id.length; i++) {
            for (int j = 0; j < user_id.length; j++) {

                String str1 = banned_id[i];
                String str2 = user_id[j];

                if (str1.length() != str2.length()) {
                    continue;
                }

                int cnt = 0;

                for (int k = 0; k < str1.length(); k++) {
                    if (str1.charAt(k) == '*' || str1.charAt(k) == str2.charAt(k)) {
                        cnt++;
                    } else {
                        break;
                    }
                }

                if (cnt == str1.length()) {
                    cnts[i].add(j);
                }
            }
        }

        visit = new boolean[user_id.length];
        combination(0, cnts, new ArrayList<>());

        return answer.size();
    }
}