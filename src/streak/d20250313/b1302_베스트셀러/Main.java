package streak.d20250313.b1302_베스트셀러;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, max;
    static Map<String, Integer> map = new HashMap<>();
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        for (String str : map.keySet()) {
            max = Math.max(max, map.get(str));
        }

        for (String str : map.keySet()) {
            if (map.get(str) == max) {
                list.add(str);
            }
        }

        Collections.sort(list);

        System.out.println(list.get(0));
    }
}
