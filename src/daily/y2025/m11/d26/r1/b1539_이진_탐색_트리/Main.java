package daily.y2025.m11.d26.r1.b1539_이진_탐색_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/*
    트리의 깊이를 구하는 방법
    1. 재귀 탐색
    2. 부모 탐색 후 깊이 + 1 (부모는 cur보다 큰 값 중 가장 작은 값, 작은 값 중 가장 큰 값 둘 중 하나)
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int root = Integer.parseInt(br.readLine());
        TreeSet<Integer> set = new TreeSet<>();
        set.add(root);

        int[] heights = new int[n + 1];
        heights[root] = 1;
        long answer = 1;

        for (int i = 0; i < n - 1; i++) {
            int num = Integer.parseInt(br.readLine());
            Integer left = set.lower(num);
            Integer right = set.higher(num);
            set.add(num);

            int leftH = left == null ? 0 : heights[left];
            int rightH = right == null ? 0 : heights[right];

            int height = Math.max(leftH, rightH) + 1;
            heights[num] = height;
            answer += height;
        }

        System.out.println(answer);
    }
}
