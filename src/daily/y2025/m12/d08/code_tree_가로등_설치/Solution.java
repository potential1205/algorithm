package daily.y2025.m12.d08.code_tree_가로등_설치;

import java.util.*;
import java.io.*;

public class Solution {

    static int q;
    static int n, m;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        q = Integer.parseInt(st.nextToken());

        int id = 1;
        int[] arr = new int[200001]; // 최초 가로등 10만개, 쿼리로 추가 가능한 가로등 10만개
        TreeSet<Integer> set = new TreeSet<>();
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 100) {
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());

                int initLamp = Integer.parseInt(st.nextToken());
                arr[id++] = initLamp;
                set.add(initLamp);
                int before = initLamp;

                for (int j = 1; j < m; j++) {
                    int lamp = Integer.parseInt(st.nextToken());
                    arr[id++] = lamp;
                    set.add(lamp);

                    int gap = lamp - before;

                    map.putIfAbsent(gap, new TreeSet<>());
                    map.get(gap).add(before);

                    before = lamp;
                }

            } else if (cmd == 200) {
                // gap이 가장 큰 집합 꺼내오기
                int gap = map.lastKey();
                TreeSet<Integer> maxSet = map.get(gap);

                int left = maxSet.first();
                maxSet.remove(left);
                if (maxSet.size() == 0) {
                    map.remove(gap);
                }

                int right = left + gap;
                int mid;
                if ((left + right) % 2 == 0) {
                    mid = (left + right) / 2;
                } else {
                    mid = (left + right + 1) / 2;
                }

                set.add(mid);
                arr[id++] = mid;

                int gap1 = mid - left;
                map.putIfAbsent(gap1, new TreeSet<>());
                map.get(gap1).add(left);

                int gap2 = right - mid;
                map.putIfAbsent(gap2, new TreeSet<>());
                map.get(gap2).add(mid);

            } else if (cmd == 300) {
                int num = Integer.parseInt(st.nextToken());
                int val = arr[num];

                if (val == set.last()) {
                    int left = set.lower(val);
                    int gapLeft = val - left;
                    TreeSet<Integer> leftSet = map.get(gapLeft);
                    leftSet.remove(left);
                    if (leftSet.size() == 0) {
                        map.remove(gapLeft);
                    }

                    set.remove(val);
                    continue;
                }

                if (val == set.first()) {
                    int right = set.higher(val);
                    int gapRight = right - val;
                    TreeSet<Integer> rightSet = map.get(gapRight);
                    rightSet.remove(val);
                    if (rightSet.size() == 0) {
                        map.remove(gapRight);
                    }

                    set.remove(val);
                    continue;
                }

                // 중간에 있는 가로등이면
                int left = set.lower(val);
                int gapLeft = val - left;
                TreeSet<Integer> leftSet = map.get(gapLeft);
                leftSet.remove(left);
                if (leftSet.size() == 0) {
                    map.remove(gapLeft);
                }

                int right = set.higher(val);
                int gapRight = right - val;
                TreeSet<Integer> rightSet = map.get(gapRight);
                rightSet.remove(val);
                if (rightSet.size() == 0) {
                    map.remove(gapRight);
                }

                set.remove(val);

                int newGap = right - left;
                map.putIfAbsent(newGap, new TreeSet<>());
                map.get(newGap).add(left);

            } else if (cmd == 400) {
                int answer = Math.max(2 * (set.first() - 1), 2 * (n - set.last()));
                answer = Math.max(answer, map.lastKey());
                System.out.println(answer);
            }

        }

    }
}