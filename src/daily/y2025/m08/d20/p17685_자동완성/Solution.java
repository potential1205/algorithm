package daily.y2025.m08.d20.p17685_자동완성;

import java.util.*;

class Solution {

    static class Node {
        int cnt; // 방문횟수
        boolean isEnd;
        Map<Character, Node> child = new HashMap<>();

        Node() {}
    }

    public int solution(String[] words) {
        int answer = 0;

        Node root = new Node();

        // 트라이 만들기
        for (String word : words) {
            Node p = root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (p.child.containsKey(ch)) {
                    p = p.child.get(ch);
                    p.cnt++;
                } else {
                    p.child.put(ch, new Node());
                    p = p.child.get(ch);
                    p.cnt++;
                }
            }

            p.isEnd = true;
        }

        // 탐색
        for (String word : words) {
            Node p = root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                Node next = p.child.get(ch);

                if (next.cnt == 1) { // 더 이상 가보지 않아도 단어가 결정되는 경우
                    answer++;
                    break;
                } else {
                    p = p.child.get(ch);
                    answer++;
                }
            }
        }

        return answer;
    }
}