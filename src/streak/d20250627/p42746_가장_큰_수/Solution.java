package streak.d20250627.p42746_가장_큰_수;

import java.util.*;

class Solution {

    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder("");
        List<String> list = new ArrayList<>();

        for (int val : numbers) {
            String str = String.valueOf(val);
            list.add(str);
        }

        Collections.sort(list, (a, b) -> (b + a).compareTo(a + b));

        for (String node : list) {
            sb.append(node);
        }

        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }
}