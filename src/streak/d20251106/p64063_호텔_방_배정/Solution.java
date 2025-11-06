package streak.d20251106.p64063_호텔_방_배정;

import java.util.*;

class Solution {

    static Map<Long, Long> map = new HashMap<>();

    static long find(long num) {
        // 빈 방을 찾았다면 반환
        if (!map.containsKey(num)) {
            return num;
        }

        // 반환하기전 경로 압축 (num to target)
        long next = find(map.get(num));
        map.put(num, next);
        return next;
    }

    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            long room = room_number[i];

            // 비어있는 방 찾기 (room 번호 이상인 경우만)
            long emptyRoom = find(room);
            answer[i] = emptyRoom;

            // emptyRoom에 방이 배정되었으므로 그 다음 빈 방 매핑하기
            long nextEmptyRoom = find(emptyRoom + 1);
            map.put(room, nextEmptyRoom); // 없어도 되지만 아주 조금 도움이 됨
            map.put(emptyRoom, nextEmptyRoom);
        }

        return answer;
    }
}