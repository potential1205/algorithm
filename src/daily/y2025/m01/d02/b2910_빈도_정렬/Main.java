package daily.y2025.m01.d02.b2910_빈도_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,C;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        HashMap<Integer,Integer> map = new LinkedHashMap<>();

        st = new StringTokenizer(bf.readLine());

        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (map.containsKey(num)) {
                map.put(num , map.get(num)+1);
            } else {
                map.put(num, 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>(map.keySet());

        Collections.sort(list, (a, b) -> Integer.compare(map.get(b), map.get(a)));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            for (int j = 0; j < map.get(value); j++) {
                sb.append(value + " ");
            }
        }

        System.out.println(sb);
    }
}
