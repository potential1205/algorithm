package daily.y2025.m09.d23.b21939_문제_추천_시스템_Version_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // 번호, 난이도
        Map<Integer, Integer> levelMap = new HashMap<>();

        // 난이도, 문제 집합
        TreeMap<Integer, TreeSet<Integer>> problemInfo = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            levelMap.put(num, level);

            if (problemInfo.containsKey(level)) {
                problemInfo.get(level).add(num);
            } else {
                problemInfo.put(level, new TreeSet<>());
                problemInfo.get(level).add(num);
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());

                levelMap.put(num, level);

                if (problemInfo.containsKey(level)) {
                    problemInfo.get(level).add(num);
                } else {
                    problemInfo.put(level, new TreeSet<>());
                    problemInfo.get(level).add(num);
                }
            } else if (cmd.equals("recommend")) {
                int type = Integer.parseInt(st.nextToken());
                if (type == 1) {
                    int level = problemInfo.lastKey();
                    int num = problemInfo.get(level).last();
                    System.out.println(num);
                } else if (type == -1) {
                    int level = problemInfo.firstKey();
                    int num = problemInfo.get(level).first();
                    System.out.println(num);
                }
            } else if (cmd.equals("solved")) {
                int num = Integer.parseInt(st.nextToken());
                int level = levelMap.get(num);
                TreeSet<Integer> set = problemInfo.get(level);
                set.remove(num);
                if (set.isEmpty()) {
                    problemInfo.remove(level);
                }
            }
        }
    }
}
