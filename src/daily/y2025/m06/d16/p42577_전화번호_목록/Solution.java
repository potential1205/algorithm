package daily.y2025.m06.d16.p42577_전화번호_목록;

import java.util.*;

class Solution {

    static class Node {
        char value;
        boolean endOfWord = false;
        Map<Character, Node> childMap = new HashMap<>();
        Node() {}
    }

    static boolean check(String[] phone_book) {
        Node root = new Node();

        for (String str : phone_book) {
            Node point = root;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                if (!point.childMap.containsKey(ch)) {
                    Node newNode = new Node();
                    newNode.value = ch;
                    if (i == (str.length()-1)) {
                        newNode.endOfWord = true;
                    }

                    point.childMap.put(ch, newNode);
                    point = newNode;

                } else {
                    point = point.childMap.get(ch);

                    if (point.endOfWord) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        return check(phone_book);
    }
}