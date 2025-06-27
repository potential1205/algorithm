package streak.d20250627.p42746_가장_큰_수;

import java.util.*;

// 반례
// [12, 121] -> "12121"
// [0,0,0] -> "0"

class Try1 {

    static class Node implements Comparable<Node> {
        int len;
        String str;
        String originStr;

        Node(int len, String str, String originStr) {
            this.len = len;
            this.str = str;
            this.originStr = originStr;
        }

        @Override
        public int compareTo(Node o) {
            if (this.str.equals(o.str)) {
                return this.len - o.len;
            } else {
                return o.str.compareTo(this.str);
            }
        }
    }

    public String solution(int[] numbers) {
        List<Node> list = new ArrayList<>();

        for (int val : numbers) {
            String str = String.valueOf(val);
            int len = str.length();

            for (int i = 0; i < 4 - len; i++) {
                str += "0";
            }

            list.add(new Node(len, str, String.valueOf(val)));
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder("");

        for (Node node : list) {
            sb.append(node.originStr);
        }

        return sb.toString();
    }
}