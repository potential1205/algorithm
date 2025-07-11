package streak.d20250712.p1845_폰켓몬;

import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }

        }

        if (nums.length / 2 >= map.size()) {
            return map.size();
        } else {
            return nums.length / 2;
        }
    }
}