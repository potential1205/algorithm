package streak.d20251104.p60060_가사_검색;

import java.util.*;

class Solution {

    // 현재 노드를 지나간 단어의 개수와 자식 요소들
    static class Node {
        int cnt;
        Node[] child = new Node[26];
    }

    // 트라이 생성
    static void makeTrie(Node root, String str) {
        Node cur = root;
        cur.cnt++;

        for (char ch : str.toCharArray()) {
            int idx = ch - 'a';

            if (cur.child[idx] == null) {
                cur.child[idx] = new Node();
            }

            // *순서 중요
            // cur를 지나는 단어의 개수이므로
            // cur 이동 후 증가
            cur = cur.child[idx];
            cur.cnt++;
        }
    }

    // 쿼리 검색
    static int search(Node root, String query) {
        Node cur = root;

        // ?를 만나기전까지 이동
        // ?를 만나면 해당 단어가 포함된 단어 개수 출력
        for (char ch : query.toCharArray()) {
            if (ch == '?') {
                return cur.cnt;
            }

            int idx = ch - 'a';
            if (cur.child[idx] != null) {
                cur = cur.child[idx];
            } else {
                return 0;
            }
        }

        return 0;
    }

    public int[] solution(String[] words, String[] queries) {

        // 정순, 역순 쿼리 모두 고려
        Map<Integer, Node> forward = new HashMap<>();
        Map<Integer, Node> backward = new HashMap<>();

        for (String word : words) {
            int size = word.length();

            if (!forward.containsKey(size)) {
                forward.put(size, new Node());
            }

            if (!backward.containsKey(size)) {
                backward.put(size, new Node());
            }

            String reverseStr = new StringBuilder(word).reverse().toString();

            // 정순, 역순 트라이 생성
            makeTrie(forward.get(size), word);
            makeTrie(backward.get(size), reverseStr);
        }

        int n = queries.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            String query = queries[i];
            int querySize = query.length();

            // 관리하고 있는 단어의 길이가 쿼리의 길이와 같은 트라이의 루트만 탐색
            if (query.charAt(0) != '?') {
                if (forward.containsKey(querySize)) {
                    answer[i] = search(forward.get(querySize), query);
                }
            } else {
                String reverseQuery = new StringBuilder(query).reverse().toString(); // ?가 접두사인 경우 쿼리도 반전
                if (backward.containsKey(querySize)) {
                    answer[i] = search(backward.get(querySize), reverseQuery);
                }
            }
        }

        return answer;
    }
}