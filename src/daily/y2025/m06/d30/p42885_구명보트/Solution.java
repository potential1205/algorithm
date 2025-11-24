package daily.y2025.m06.d30.p42885_구명보트;

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int n = people.length;
        int x1 = 0;
        int x2 = n - 1;

        Arrays.sort(people);

        while (x1 <= x2) {
            if (people[x1] + people[x2] <= limit) {
                answer++;
                x1++;
                x2--;
            } else {
                answer++;
                x2--;
            }
        }

        return answer;
    }
}