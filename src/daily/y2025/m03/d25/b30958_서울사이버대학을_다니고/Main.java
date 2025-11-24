package daily.y2025.m03.d25.b30958_서울사이버대학을_다니고;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int answer = 0;

        int n = sc.nextInt();
        String blank = sc.nextLine();
        String str = sc.nextLine();

        Map<Character, Integer> map = new HashMap<>();

        for (char ch : str.toCharArray()) {
            if ('a' <= ch && ch <= 'z') {
                if (map.containsKey(ch)) {
                    int cnt = map.get(ch) + 1;
                    map.put(ch, cnt);
                    answer = Math.max(answer ,cnt);
                } else {
                    int cnt = 1;
                    map.put(ch, cnt);
                    answer = Math.max(answer ,cnt);
                }
            }

        }

        System.out.println(answer);
    }
}
