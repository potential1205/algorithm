package daily.y2025.m07.d02.p42840_모의고사;

import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] cnts = {0, 0, 0};

        int[] p1 = {1, 2, 3, 4, 5};
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        for (int i = 0; i < answers.length; i++) {
            int answer = answers[i];

            if (p1[i % 5] == answer) {
                cnts[0] = cnts[0] + 1;
            }

            if (p2[i % 8] == answer) {
                cnts[1] = cnts[1] + 1;
            }

            if (p3[i % 10] == answer) {
                cnts[2] = cnts[2] + 1;
            }
        }

        int maxVal = 0;

        for (int i = 0; i < 3; i++) {
            if (maxVal < cnts[i]) {
                maxVal = cnts[i];
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (cnts[i] == maxVal) {
                list.add(i+1);
            }
        }

        int[] arr = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
}