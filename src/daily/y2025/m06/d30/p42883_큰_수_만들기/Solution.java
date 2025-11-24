package daily.y2025.m06.d30.p42883_큰_수_만들기;

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder("");

        int len = number.length();
        int n = len - k;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && stack.peek() < number.charAt(i) && k > 0) {
                stack.pop();
                k--;
            }

            stack.push(number.charAt(i));
        }

        for (int i = 0; i < n; i++) {
            answer.append(stack.get(i));
        }

        return answer.toString();
    }
}