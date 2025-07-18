package streak.d20250718.p67258_보석_쇼핑;

import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int left;
        int right;

        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            int range = (this.right - this.left) - (o.right - o.left);

            if (range == 0) {
                return this.left - o.left;
            } else {
                return range;
            }
        }
    }

    public int[] solution(String[] gems) {
        Set<String> stateSet = new HashSet<>();
        Map<String, Integer> stateMap = new HashMap<>();

        for (String gem : gems) stateMap.put(gem, 0);

        int left = 0;
        int right = 0;
        int gemTypeCount = stateMap.size();

        stateSet.add(gems[0]);
        stateMap.put(gems[0], 1);

        List<Node> nodeList = new ArrayList<>();

        while (true) {
            if (stateSet.size() == gemTypeCount) {
                nodeList.add(new Node(left, right));

                stateMap.put(gems[left], stateMap.get(gems[left]) - 1);
                if (stateMap.get(gems[left]) == 0) {
                    stateSet.remove(gems[left]);
                }

                left++;
            } else {
                right++;
                if (right >= gems.length) break;
                stateMap.put(gems[right], stateMap.get(gems[right]) + 1);
                stateSet.add(gems[right]);
            }

        }

        Collections.sort(nodeList);

        return new int[] {nodeList.get(0).left + 1, nodeList.get(0).right + 1};
    }
}