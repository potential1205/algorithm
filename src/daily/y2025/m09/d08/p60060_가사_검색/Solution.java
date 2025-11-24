package daily.y2025.m09.d08.p60060_가사_검색;

import java.util.*;

class Solution {

    static class Node {
        int cnt;
        Node[] child = new Node[26];
    }

    static Map<Integer, Node> forward = new HashMap<>();
    static Map<Integer, Node> backward = new HashMap<>();
    static Map<Integer, Integer> lenCount = new HashMap<>();

    static boolean isAllWild(String q) {
        for (int i = 0; i < q.length(); i++) {
            if (q.charAt(i) != '?') {
                return false;
            }
        }
        return true;
    }

    static void insert(Node root, String str) {
        Node cur = root;
        cur.cnt++;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if (cur.child[idx] == null) {
                cur.child[idx] = new Node();
            }
            cur = cur.child[idx];
            cur.cnt++;
        }
    }

    static int count(Node root, String q) {
        Node cur = root;
        for (int i = 0; i < q.length(); i++) {
            char ch = q.charAt(i);
            if (ch == '?') return cur.cnt;
            int idx = ch - 'a';
            if (cur.child[idx] == null) return 0;
            cur = cur.child[idx];
        }
        return cur.cnt;
    }

    public int[] solution(String[] words, String[] queries) {
        // 트라이 만들기
        for (String str : words) {
            int n = str.length();
            lenCount.put(n, lenCount.getOrDefault(n, 0) + 1);

            forward.computeIfAbsent(n, key -> new Node());
            backward.computeIfAbsent(n, key -> new Node());

            insert(forward.get(n), str);
            insert(backward.get(n), new StringBuilder(str).reverse().toString());
        }

        // 쿼리 실행
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int n = query.length();

            if (!lenCount.containsKey(n)) { // 해당 길이 없음
                answer[i] = 0;
                continue;
            }

            if (isAllWild(query)) { // 전부 ?
                answer[i] = lenCount.get(n);
                continue;
            }

            if (query.charAt(0) != '?') {
                answer[i] = count(forward.get(n), query);
            } else {
                String reverseQuery = new StringBuilder(query).reverse().toString();
                answer[i] = count(backward.get(n), reverseQuery);
            }
        }

        return answer;
    }
}