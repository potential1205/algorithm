package streak.d20250925.p150366_표_병합;

import java.util.*;

class Solution {

    static int find(int[] parents, int cur) {
        if (parents[cur] == cur) {
            return cur;
        }

        return parents[cur] = find(parents, parents[cur]);
    }

    public List<String> solution(String[] commands) {
        List<String> list = new ArrayList<>();

        int[] parents = new int[2501];
        String[] values = new String[2501];
        for (int i = 1; i <= 2500; i++) {
            parents[i] = i;
        }

        for (String command : commands) {
            String[] cmd = command.split(" ");

            if (cmd[0].equals("UPDATE") && cmd.length == 4) {
                int y = Integer.valueOf(cmd[1]);
                int x = Integer.valueOf(cmd[2]);
                int idx = (y - 1) * 50 + x;
                int root = find(parents, idx);
                values[root] = cmd[3];
            } else if (cmd[0].equals("UPDATE") && cmd.length == 3) {
                for (int i = 1; i <= 2500; i++) {
                    if (parents[i] == i && cmd[1].equals(values[i])) {
                        values[i] = cmd[2];
                    }
                }
            } else if (cmd[0].equals("MERGE")) {
                int y1 = Integer.valueOf(cmd[1]);
                int x1 = Integer.valueOf(cmd[2]);
                int y2 = Integer.valueOf(cmd[3]);
                int x2 = Integer.valueOf(cmd[4]);
                int idx1 = (y1 - 1) * 50 + x1;
                int idx2 = (y2 - 1) * 50 + x2;
                int root1 = find(parents, idx1);
                int root2 = find(parents, idx2);
                if (root1 == root2) continue;

                if (values[root1] != null && values[root2] == null) {
                    parents[root2] = root1;
                } else if (values[root1] == null && values[root2] != null) {
                    parents[root1] = root2;
                } else if (values[root1] != null && values[root2] != null) {
                    parents[root2] = root1;
                } else {
                    parents[root2] = root1;
                }
            } else if (cmd[0].equals("UNMERGE")) {
                int y = Integer.valueOf(cmd[1]);
                int x = Integer.valueOf(cmd[2]);
                int idx = (y - 1) * 50 + x;
                int root = find(parents, idx);
                String value = values[root];

                List<Integer> updateList = new ArrayList<>();
                for (int i = 1; i <= 2500; i++) {
                    if (find(parents, i) == root) {
                        updateList.add(i);
                    }
                }

                for (int i : updateList) {
                    parents[i] = i;
                    values[i] = null;
                }
                values[idx] = value;

            } else if (cmd[0].equals("PRINT")) {
                int y = Integer.valueOf(cmd[1]);
                int x = Integer.valueOf(cmd[2]);
                int idx = (y - 1) * 50 + x;
                int root = find(parents, idx);
                String value = values[root];

                if (value == null) {
                    list.add("EMPTY");
                } else {
                    list.add(value);
                }
            }
        }

        return list;
    }
}