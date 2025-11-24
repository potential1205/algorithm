package daily.y2025.m11.d06.p64063_호텔_방_배정;

import java.util.*;

class Timeout {

    static Map<Long, Long> map = new HashMap<>();

    static long find(long num) {
        if (!map.containsKey(num)) {
            return num;
        }

        return find(map.get(num));
    }


    public long[] solution(long k, long[] room_number) {

        int n = room_number.length;

        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            long room = room_number[i];

            if (!map.containsKey(room)) {
                answer[i] = room;
                long emptyRoom = find(room + 1);
                map.put(room, emptyRoom);
            } else{
                long emptyRoom = find(room + 1);
                answer[i] = emptyRoom;
                long nextEmptyRoom = find(emptyRoom + 1);
                map.put(emptyRoom, nextEmptyRoom);
            }
        }


        return answer;
    }
}