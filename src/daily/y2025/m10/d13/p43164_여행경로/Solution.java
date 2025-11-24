package daily.y2025.m10.d13.p43164_여행경로;

import java.util.*;

class Solution {
    static int n;
    static List<String> answer = new ArrayList<>();
    static Map<String, List<String>> map = new HashMap<>();
    static Map<String, boolean[]> visit = new HashMap<>();

    static void dfs(String spot, String path, int cnt) {
        if (cnt == n + 1) {
            answer.add(path);
            return;
        }

        List<String> list = map.get(spot);

        for (int i = 0; i < list.size(); i++) {
            boolean[] visited = visit.get(spot);
            if (visited[i]) continue;
            visited[i] = true;
            dfs(list.get(i), path + "," + list.get(i), cnt + 1);
            visited[i] = false;
        }
    }

    public String[] solution(String[][] tickets) {
        n = tickets.length;

        Set<String> set = new HashSet<>();
        for (String[] ticket : tickets) {
            set.add(ticket[0]);
            set.add(ticket[1]);
        }

        for (String point : set) {
            map.put(point, new ArrayList<>());
        }

        for (String[] ticket : tickets) {
            if (map.containsKey(ticket[0])) {
                map.get(ticket[0]).add(ticket[1]);
            }
        }

        for (String key : map.keySet()) {
            int size = map.get(key).size();
            visit.put(key, new boolean[size]);
        }

        dfs("ICN", "ICN", 1);
        Collections.sort(answer);

        return answer.get(0).split(",");
    }
}