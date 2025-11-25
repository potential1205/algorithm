package daily.y2025.m11.d25.b1539_이진_검색_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int rootNum = Integer.parseInt(br.readLine());
        int[] depth = new int[n];
        TreeSet<Integer> set = new TreeSet<>();
        set.add(rootNum);
        depth[rootNum] = 1;
        long answer = 1;

        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            Integer left = set.lower(num);
            Integer right = set.higher(num);
            set.add(num);

            int leftDepth = (left == null) ? 0 : depth[left];
            int rightDepth = (right == null) ? 0 : depth[right];

            depth[num] = Math.max(leftDepth, rightDepth) + 1;
            answer += depth[num];
        }

        System.out.println(answer);
    }
}
