package streak.d20250411.b1755_숫자놀이;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    static Map<String, String> map = new TreeMap<>();

    static Map<String, String> convertMap = Map.of(
            "0", "zero",
            "1", "one",
            "2", "two",
            "3", "three",
            "4", "four",
            "5", "five",
            "6", "six",
            "7", "seven",
            "8", "eight",
            "9", "nine"
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = n; i <= m; i++) {
            String num = String.valueOf(i);

            String result = "";

            for (char ch : num.toCharArray()) {
                String val = convertMap.get(String.valueOf(ch));
                result += val;
            }

            map.put(result, num);
        }

        int i = 0;

        for (String key : map.keySet()) {
            if (i == 10) {
                System.out.println();
                i = 0;
            }
            System.out.print(map.get(key) + " ");
            i++;
        }
    }
}
