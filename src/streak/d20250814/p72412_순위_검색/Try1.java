package streak.d20250814.p72412_순위_검색;

import java.util.*;

// 효율성 테스트 : 시간초과
class Try1 {
    public int[] solution(String[] info, String[] query) {
        Set<Integer> cpp = new HashSet<>();
        Set<Integer> java = new HashSet<>();
        Set<Integer> python = new HashSet<>();

        Set<Integer> backend = new HashSet<>();
        Set<Integer> frontend = new HashSet<>();

        Set<Integer> junior = new HashSet<>();
        Set<Integer> senior = new HashSet<>();

        Set<Integer> chicken = new HashSet<>();
        Set<Integer> pizza = new HashSet<>();

        int n = info.length;
        int m = query.length;

        int[] score = new int[n];
        int[] result = new int[m];

        Set<Integer> temp = new HashSet<>();

        for (int i = 0; i < info.length; i++) {
            String line = info[i];
            String[] str = line.split(" ");
            temp.add(i);

            if (str[0].equals("cpp")) {
                cpp.add(i);
            } else if (str[0].equals("java")) {
                java.add(i);
            } else if (str[0].equals("python")) {
                python.add(i);
            }

            if (str[1].equals("backend")) {
                backend.add(i);
            } else if (str[1].equals("frontend")) {
                frontend.add(i);
            }

            if (str[2].equals("junior")) {
                junior.add(i);
            } else if (str[2].equals("senior")) {
                senior.add(i);
            }

            if (str[3].equals("chicken")) {
                chicken.add(i);
            } else if (str[3].equals("pizza")) {
                pizza.add(i);
            }

            score[i] = Integer.valueOf(str[4]);
        }

        int idx = 0;
        for (String line : query) {
            String[] str = line.split(" ");

            Set<Integer> answer = new HashSet<>(temp);

            if (str[0].equals("cpp")) {
                answer.retainAll(cpp);
            } else if (str[0].equals("java")) {
                answer.retainAll(java);
            } else if (str[0].equals("python")) {
                answer.retainAll(python);
            }

            if (str[2].equals("backend")) {
                answer.retainAll(backend);
            } else if (str[2].equals("frontend")) {
                answer.retainAll(frontend);
            }

            if (str[4].equals("junior")) {
                answer.retainAll(junior);
            } else if (str[4].equals("senior")) {
                answer.retainAll(senior);
            }

            if (str[6].equals("chicken")) {
                answer.retainAll(chicken);
            } else if (str[6].equals("pizza")) {
                answer.retainAll(pizza);
            }


            if (!str[7].equals("-")) {
                int std = Integer.valueOf(str[7]);
                int cnt = 0;
                for (int id : answer) {
                    if (score[id] >= std) {
                        cnt++;
                    }
                }

                result[idx++] = cnt;
            } else {
                result[idx++] = answer.size();
            }
        }

        return result;
    }
}