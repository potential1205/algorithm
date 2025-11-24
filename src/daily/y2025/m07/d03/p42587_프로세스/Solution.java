package daily.y2025.m07.d03.p42587_프로세스;

import java.util.*;

class Solution {

    public int solution(int[] priorities, int location) {
        int n = priorities.length;
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            list.add(priorities[i]);
            queue.offer(i);
        }

        Collections.sort(list, Collections.reverseOrder());

        int cnt = 1;
        int answer = 0;

        while (!queue.isEmpty()) {
            int process = queue.poll();

            if (list.get(0) > priorities[process]) {
                queue.offer(process);
            } else {
                list.remove(0);

                if (location == process) {
                    answer = cnt;
                    break;
                }

                cnt++;
            }
        }

        return answer;
    }
}