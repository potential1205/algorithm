package daily.y2025.m02.d17.b1021_회전하는_큐;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        LinkedList<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }

        int count = 0;

        for(int i = 0; i < m; i++) {
            int num = sc.nextInt();

            while(deque.getFirst() != num) {
                if(deque.indexOf(num) <= (deque.size() / 2)) {
                    deque.addLast(deque.getFirst());
                    deque.removeFirst();
                }else {
                    deque.addFirst(deque.getLast());
                    deque.removeLast();
                }
                count++;
            }

            if(deque.getFirst() == num) {
                deque.removeFirst();
            }
        }
        System.out.println(count);

    }
}
