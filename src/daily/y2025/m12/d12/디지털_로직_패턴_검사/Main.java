package daily.y2025.m12.d12.디지털_로직_패턴_검사;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int k = scanner.nextInt();
        int m = scanner.nextInt();

        Map<Long, Integer> map = new HashMap<>();

        long val = 0;
        long mask = (1L << k) - 1; // 지릿수 유지용
        int answer = 0;

        for (int i = 0; i < k; i++) {
            val = (val << 1) | (s.charAt(i) - '0');
        }

        map.put(val, 1);

        for (int i = k; i < s.length(); i++) {
            val = ((val << 1) | (s.charAt(i) - '0')) & mask;
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        for (Long key : map.keySet()) {
            if (map.get(key) >= m) {
                answer = 1;
                break;
            }
        }

        System.out.println(answer);
    }
}