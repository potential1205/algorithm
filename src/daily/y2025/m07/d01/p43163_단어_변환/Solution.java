package daily.y2025.m07.d01.p43163_단어_변환;

import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static int[] visit;
    static int len;

    static class Node {
        int cnt;
        int idx;
        String str;

        Node(int cnt, int idx, String str) {
            this.cnt = cnt;
            this.idx = idx;
            this.str = str;
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

    static int bfs(String begin, String target, String[] words) {
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            if (isSingleDiff(begin, words[i])) {
                queue.offer(new Node(1, i, words[i]));
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.str.equals(target)) {
                return node.cnt;
            }

            for (int i = 0; i < len; i++) {
                if (visit[i] != 0 && visit[i] <= node.cnt) {
                    continue;
                }

                if (isSingleDiff(node.str, words[i])) {
                    queue.offer(new Node(node.cnt + 1, i, words[i]));
                    visit[i] = node.cnt + 1;
                }
            }
        }

        return 0;
    }

    public int solution(String begin, String target, String[] words) {
        len = words.length;
        visit = new int[len];

        return bfs(begin, target, words);
    }
}