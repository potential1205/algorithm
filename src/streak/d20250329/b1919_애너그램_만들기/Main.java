package streak.d20250329.b1919_애너그램_만들기;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i< 26; i++) {
            map1.put((char)(97+i), 0);
            map2.put((char)(97+i), 0);
        }

        for (char ch : str1.toCharArray()) {
            map1.put(ch, map1.get(ch) + 1);
        }

        for (char ch : str2.toCharArray()) {
            map2.put(ch, map2.get(ch) + 1);
        }

        int answer = 0;

        for (int i = 0; i < 26; i++) {
            char ch = (char)(97+i);
            if (map1.get(ch) == 0 && map2.get(ch) == 0) {
                continue;
            } else if (map1.get(ch) == 0 && map2.get(ch) != 0) {
                answer += map2.get(ch);
            } else if (map1.get(ch) != 0 && map2.get(ch) == 0) {
                answer += map1.get(ch);
            } else if (map1.get(ch) != 0 && map2.get(ch) != 0) {
                answer += Math.abs(map1.get(ch) - map2.get(ch));
            }
        }

        System.out.println(answer);
    }
}
