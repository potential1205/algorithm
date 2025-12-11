package daily.y2025.m12.d11.code_tree_출퇴근길;

import java.util.*;

public class Try1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        HashMap<Integer, ArrayDeque<Integer>> startMap = new HashMap<>();
        HashMap<Integer, ArrayDeque<Integer>> endMap = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            startMap.put(i, new ArrayDeque<>());
            endMap.put(i, new ArrayDeque<>());
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            startMap.get(x).offer(y);
            endMap.get(x).offer(y);
        }
        int S = sc.nextInt();
        int T = sc.nextInt();

        // START 초기화
        Set<Integer> startSet = new HashSet<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        while (!startMap.get(S).isEmpty()) {
            int cur = startMap.get(S).poll();
            queue.offer(cur);
            startSet.add(cur);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == T) continue;
            while (!startMap.get(cur).isEmpty()) {
                int next = startMap.get(cur).poll();
                queue.offer(next);
                startSet.add(next);
            }
        }

        // END 초기화
        Set<Integer> endSet = new HashSet<>();
        queue = new ArrayDeque<>();
        while (!endMap.get(T).isEmpty()) {
            int cur = endMap.get(T).poll();
            queue.offer(cur);
            endSet.add(cur);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == S) continue;
            while (!endMap.get(cur).isEmpty()) {
                int next = endMap.get(cur).poll();
                queue.add(next);
                endSet.add(next);
            }
        }

        for (int num : startSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (int num : endSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        startSet.retainAll(endSet);
        startSet.remove(S);
        startSet.remove(T);
        for (int num : startSet) {
            System.out.print(num + " ");
        }

        //System.out.println(startSet.size());
    }
}