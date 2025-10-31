package streak.d20251031.p60060_가사_검색;

import java.util.*;

class Solution {

    // 문자열 길이별 트라이 관리
    // forward : word 정순 트라이 저장
    // backward : word 역순 트라이 저장
    // why? : ?로 시작하는 쿼리를 처리할 때 효율적임, 사실상 각 단어마다 두 개의 트라이에서 관리
    static Map<Integer, Node> forward = new HashMap<>();
    static Map<Integer, Node> backward = new HashMap<>();

    static class Node {
        int cnt;
        Node[] child = new Node[26];
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

    static int search(Node root, String query) {
        Node cur = root;

        for (char ch : query.toCharArray()) {
            if (ch == '?') {
                return cur.cnt;
            } else {
                int idx = ch - 'a';
                if (cur.child[idx] == null) return 0;
                cur = cur.child[idx];
            }
        }

        return 0;
    }

    public int[] solution(String[] words, String[] queries) {

        for (String str : words) {
            int len = str.length();

            if (!forward.containsKey(len)) {
                forward.put(len, new Node());
            }

            if (!backward.containsKey(len)) {
                backward.put(len, new Node());
            }

            insert(forward.get(len), str);
            insert(backward.get(len), new StringBuilder(str).reverse().toString());
        }

        int n = queries.length;
        int[] answer = new int[n];

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int len = query.length();

            // ?로 시작하지 않고, 해당 길이의 단어들을 관리하는 트라이가 존재하는 경우
            if (query.charAt(0) != '?') {
                if (forward.containsKey(len)) {
                    answer[i] = search(forward.get(len), queries[i]);
                }

                // ?로 시작하고, 해당 길이의 단어들을 관리하는 트라이가 존재하는 경우
                // 주의! : 쿼리도 역순으로 변환 후 조회하기
            } else {
                if (backward.containsKey(len)) {
                    String reverseQuery = new StringBuilder(query).reverse().toString();
                    answer[i] = search(backward.get(len), reverseQuery);
                }
            }
        }

        return answer;
    }
}