package daily.y2025.m07.d09.p12909_올바른_괄호;

import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (stack.peek() == '(' && s.charAt(i) == ')') {
                stack.pop();
            } else if (stack.peek() == ')' && s.charAt(i) == '(') {
                return false;
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}