package streak.d20250701.p43163_단어_변환;

import java.util.*;

class Try1 {
    static int answer = Integer.MAX_VALUE;
    static int size;
    static boolean[] visit;
    static int[] dx = {-1, 1};

    static class Node {
        int cnt;
        int x;

        Node(int cnt, int x) {
            this.cnt = cnt;
            this.x = x;
        }

    }

    static boolean isSingleDiff(String str1, String str2) {
        int cnt = 0;
        int len = str1.length();

        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                cnt++;
            }
        }

        if (cnt == (len - 1)) {
            return true;
        } else {
            return false;
        }
    }

    static void bfs(int sx, List<String> list, String target) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, sx));
        visit[sx] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (list.get(node.x).equals(target)) {
                answer = Math.min(answer, node.cnt);
                return;
            }

            for (int i = 0; i < 2; i++) {
                int nx = node.x + dx[i];

                if (nx < 0 || nx >= size) continue;

                if (isSingleDiff(list.get(node.x), list.get(nx))) {
                    queue.offer(new Node(node.cnt + 1, nx));
                    visit[nx] = true;
                }
            }

        }
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        List<String> list = new ArrayList<>();

        int len = words.length;

        for (int i = 0; i < len; i++) {
            list.add(words[i]);
        }

        list.add(begin);

        Collections.sort(list);

        int startIdx = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(begin)) {
                startIdx = i;
                break;
            }
        }

        size = list.size();
        visit = new boolean[size];

        System.out.println(list);

        //bfs(startIdx, list, target);

        return answer;
    }
}