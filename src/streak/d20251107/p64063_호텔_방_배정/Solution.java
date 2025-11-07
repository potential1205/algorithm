package streak.d20251107.p64063_호텔_방_배정;

import java.util.*;

class Solution {

    // 사용중인 방 번호, 그 다음 빈 방 번호
    static Map<Long, Long> map = new HashMap<>();

    static long find(long num) {

        // 비어있는 방을 발견하면 번호 반환
        if (!map.containsKey(num)) {
            return num;
        }

        // 그렇지 않다면 다음 방 탐색
        long next = map.get(num);
        long nextEmptyNum = find(next);

        // 경로 압축 (재귀로 방문했던 모든 경로에 대해서 최종 목적지 저장)
        map.put(num, nextEmptyNum);

        return nextEmptyNum;
    }

    public long[] solution(long k, long[] room_number) {

        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < room_number.length; i++) {

            // 배정할 방 탐색
            long assignedNum = find(room_number[i]);

            // 배정받은 방 다음 빈 방 탐색
            long nextEmptyNum = find(assignedNum + 1);

            // 배정받은 방 다음 빈 방 저장
            map.put(assignedNum, nextEmptyNum);
            answer[i] = assignedNum;
        }

        return answer;
    }
}