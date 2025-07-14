package streak.d20250714.p42888_오픈채팅방;

import java.util.*;

class Solution {

    public String[] solution(String[] record) {

        Map<String, String> userMap = new HashMap<>();

        List<String> answer = new ArrayList<>();
        int n = record.length;

        for (int i = 0; i < n; i++) {
            String[] cmds = record[i].split(" ");

            String cmd = cmds[0];
            String userId = cmds[1];


            if (!cmd.equals("Leave")) {
                String nickname = cmds[2];
                userMap.put(userId, nickname);
            }
        }

        for (int i = 0; i < n; i++) {
            String[] cmds = record[i].split(" ");

            if (cmds[0].equals("Enter")) {
                answer.add(userMap.get(cmds[1]) + "님이 들어왔습니다.");
            } else if (cmds[0].equals("Leave")) {
                answer.add(userMap.get(cmds[1]) + "님이 나갔습니다.");
            }
        }

        String[] temp = new String[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            temp[i] = answer.get(i);
        }

        return temp;
    }
}