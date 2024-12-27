package streak.d20241216;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int N;
    static Scanner sc = new Scanner(System.in);

    static void solve() {
        String data = sc.next();

        Stack<Character> stack = new Stack<>();

        for (int j = 0; j < data.length(); j++) {
            if (data.charAt(j) == '(') {
                stack.add(data.charAt(j));
            } else {

                if (stack.isEmpty()) {
                    System.out.println("NO");
                    return;
                }

                char ch = stack.peek();

                if (ch == '(') {
                    stack.pop();
                } else {
                    System.out.println("NO");
                    return;
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            solve();
        }
    }
}
