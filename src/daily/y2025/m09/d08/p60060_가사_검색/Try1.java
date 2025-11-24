package daily.y2025.m09.d08.p60060_가사_검색;

import java.util.*;

class Try1 {

    static class Node {
        boolean isEnd;
        int cnt;
        char ch;
        Map<Character, Node> child = new HashMap<>();

        Node() {}

        Node(char ch, int cnt, boolean isEnd) {
            this.ch = ch;
            this.cnt = cnt;
            this.isEnd = isEnd;
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Node root = new Node();

        // 트라이 만들기
        for (String str : words) {
            Node cur = root;

            for (char ch : str.toCharArray()) {
                if (!cur.child.containsKey(ch)) {
                    cur.child.put(ch, new Node(ch, 1, false));
                    cur = cur.child.get(ch);
                } else {
                    cur = cur.child.get(ch);
                    cur.cnt++;
                }
            }

            cur.isEnd = true;
        }

        // 쿼리 실행
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int n = query.length();
            int result = dfs(n, 0, query, root);
            answer[i] = result;
        }

        return answer;
    }

    static int dfs(int n, int depth, String query, Node cur) {
        if (n == depth) {
            return cur.cnt;
        }

        char ch = query.charAt(depth);
        int cnt = 0;

        if (ch != '?' && cur.child.containsKey(ch)) {
            cnt += dfs(n, depth + 1, query, cur.child.get(ch));
        } else if (ch == '?') {
            for (int i = 0; i < 26; i++) {
                char next = (char) (97 + i);
                if (cur.child.containsKey(next)) {
                    cnt += dfs(n, depth + 1, query, cur.child.get(next));
                }
            }
        }

        return cnt;
    }
}
