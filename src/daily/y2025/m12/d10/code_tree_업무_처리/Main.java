package daily.y2025.m12.d10.code_tree_업무_처리;

import java.util.*;

public class Main {

    static int h, k, r;
    static int leafCount;
    static Node[] tree;

    static class Node {
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 내가 처리할 작업
        ArrayDeque<Integer> leftQ = new ArrayDeque<>(); // 왼쪽 자식이 올려준 작업
        ArrayDeque<Integer> rightQ = new ArrayDeque<>(); // 오른쪽 자식이 올려준 작업
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        h = sc.nextInt();
        k = sc.nextInt();
        r = sc.nextInt();
        leafCount = (int) Math.pow(2, h);

        // tree 노드 수는 2^(n+1)
        tree = new Node[2 * leafCount];
        for (int i = 1; i < (2 * leafCount); i++) {
            tree[i] = new Node();
        }

        // 리프 노드에 작업 추가
        for (int i = 0; i < leafCount; i++) {
            for (int j = 0; j < k; j++) {
                int task = sc.nextInt();
                int startIdx = (int) Math.pow(2, h);
                tree[startIdx + i].queue.offerFirst(task);
            }
        }

        int answer = 0;
        for (int day = 1; day <= r; day++) {

            // 하위 뎁스부터 처리 (리프부터 루트 순으로)
            for (int depth = h; depth >= 0; depth--) {
                int nodeCnt = (int) Math.pow(2, depth);

                // 해당하는 뎁스의 노드 처리
                for (int i = 0; i < nodeCnt; i++) { // 해당 깊이의 노드 수
                    int cur = nodeCnt + i;

                    // 자기 일 부터 끝내기
                    if (!tree[cur].queue.isEmpty()) {
                        int task = tree[cur].queue.pollLast();

                        if (cur == 1) {
                            answer += task;
                        } else {
                            int parent = cur / 2;
                            if (cur % 2 == 0) {
                                tree[parent].leftQ.offerFirst(task);
                            } else if (cur % 2 != 0) {
                                tree[parent].rightQ.offerFirst(task);
                            }
                        }
                    }

                    // 날짜보고 내 left, right queue로 옮기기
                    if (day % 2 == 0 && !tree[cur].leftQ.isEmpty()) {
                        int newTask = tree[cur].leftQ.pollLast();
                        tree[cur].queue.offerFirst(newTask);
                    }

                    if (day % 2 != 0 && !tree[cur].rightQ.isEmpty()) {
                        int newTask = tree[cur].rightQ.pollLast();
                        tree[cur].queue.offerFirst(newTask);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
