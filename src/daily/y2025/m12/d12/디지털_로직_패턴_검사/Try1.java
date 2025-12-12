package daily.y2025.m12.d12.디지털_로직_패턴_검사;

import java.util.*;
import java.io.*;

public class Try1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int k = scanner.nextInt();
        int m = scanner.nextInt();
        int answer = 0;

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length() - k; i++) {
            String str = "";
            for (int j = 0; j < k; j++) {
                str += s.charAt(i + j);
            }

            map.put(str, map.getOrDefault(str, 0) + 1);

            if (map.get(str) >= m) {
                answer = 1;
                break;
            }
        }

        System.out.println(answer);
    }
}
