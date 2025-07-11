package streak.d20250711.p43164_여행경로;

import java.util.*;

class Solution {
    static boolean isEnd = false;
    static String[] answer;

    static void dfs(int depth, int n, Map<String, List<String>> graph, String key, String path, Map<String, List<Boolean>> visit) {
        if (isEnd) return;

        if (n == depth) {
            System.out.println(path);
            String[] temp = path.split(" ");

            for (int i = 0; i < temp.length; i++) {
                answer[i] = temp[i];
            }

            isEnd = true;
            return;
        }

        List<String> nodeList = graph.get(key);

        for (int i = 0; i < nodeList.size(); i++) {
            if (visit.get(key).get(i)) continue;
            visit.get(key).set(i, true);
            dfs(depth + 1, n, graph, nodeList.get(i), path + " " + nodeList.get(i), visit);
            visit.get(key).set(i, false);
        }

    }

    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        int idx = 1;

        answer = new String[n + 1];
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, List<Boolean>> visit = new HashMap<>();

        for (String[] strArr : tickets) {
            graph.put(strArr[0], new ArrayList<>());
            graph.put(strArr[1], new ArrayList<>());
            visit.put(strArr[0], new ArrayList<>());
            visit.put(strArr[1], new ArrayList<>());
        }

        for (String[] strArr : tickets) {
            graph.get(strArr[0]).add(strArr[1]);
            visit.get(strArr[0]).add(false);
        }

        for (String key : graph.keySet()) {
            Collections.sort(graph.get(key));
        }

        dfs(0, n, graph, "ICN", "ICN", visit);

        return answer;
    }
}