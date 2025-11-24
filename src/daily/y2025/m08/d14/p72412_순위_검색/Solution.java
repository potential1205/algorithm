package daily.y2025.m08.d14.p72412_순위_검색;

import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int n = info.length;
        int m = query.length;
        int[] result = new int[m];

        String[] con1 = {"-", "cpp", "java", "python"};
        String[] con2 = {"-", "backend", "frontend"};
        String[] con3 = {"-", "junior", "senior"};
        String[] con4 = {"-", "chicken", "pizza"};

        Map<String, List<Integer>> map = new HashMap<>();

        for (String c1 : con1) {
            for (String c2 : con2) {
                for (String c3 : con3) {
                    for (String c4 : con4) {
                        String key = c1 + " " + c2 + " " + c3 + " " + c4;
                        List<Integer> list = new ArrayList<>();

                        for (int i = 0; i < n; i++) {
                            String line = info[i];
                            String[] str = line.split(" ");

                            int cnt = 0; // 조건에 다 맞아야 넣을 수 있음

                            if (c1.equals("-")) {
                                cnt++;
                            } else if (c1.equals(str[0])) {
                                cnt++;
                            }

                            if (c2.equals("-")) {
                                cnt++;
                            } else if (c2.equals(str[1])) {
                                cnt++;
                            }

                            if (c3.equals("-")) {
                                cnt++;
                            } else if (c3.equals(str[2])) {
                                cnt++;
                            }

                            if (c4.equals("-")) {
                                cnt++;
                            } else if (c4.equals(str[3])) {
                                cnt++;
                            }

                            if (cnt == 4) {
                                list.add(Integer.valueOf(str[4]));
                            }
                        }

                        if (list.size() > 0) {
                            map.put(key, list);
                        } else {
                            map.put(key, new ArrayList<>());
                        }
                    }
                }
            }
        }

        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }

        int idx = 0;
        for (String line : query) {
            String[] str = line.split(" ");
            String key = str[0] + " " + str[2] + " " + str[4] + " " + str[6];
            int std = Integer.valueOf(str[7]);
            List<Integer> list = map.get(key);
            int index = lowerBound(list, std);
            result[idx++] = list.size() - index;
        }

        return result;
    }

    static int lowerBound(List<Integer> list, int std) {
        int left = 0;
        int right = list.size() - 1;
        int target = list.size();

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) >= std) {
                target = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return target;
    }
}