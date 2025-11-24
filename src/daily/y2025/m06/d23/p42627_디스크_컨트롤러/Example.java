package daily.y2025.m06.d23.p42627_디스크_컨트롤러;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example {

    static class User {
        int id;
        boolean active;

        public User(int id, boolean active) {
            this.id = id; this.active = active;
        }
    }

    public static void main(String[] args) {

        // 1. 가장 긴 문자열을 구하세요. (없으면 "없음")
        List<String> input1 = Arrays.asList("java", "stream", "lambda", "api");

        String result1 = input1.stream()
                .max(Comparator
                        .comparing((String s) -> s.length())
                        .thenComparing((String s2) -> s2))
                        .orElse("");

        System.out.println(result1);


        // 2. 짝수만 골라 제곱하고 내림차순 정렬한 리스트를 구하세요.
        List<Integer> input2 = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> result2 = input2.stream()
                .filter((val) -> val % 2 == 0)
                .map((val) -> val * val)
                .sorted(Comparator.comparing((Integer val1) -> val1))
                .collect(Collectors.toList());

        System.out.println(result2);

        // 3. 각 단어의 첫 글자로 그룹핑하세요. (예: 'j' → ["java", "junit"])
        List<String> input3 = Arrays.asList("java", "stream", "junit", "lambda");

        Map<Character, List<String>> result3 = input3.stream()
                .collect(Collectors.groupingBy((String str) -> str.charAt(0)));

        System.out.println(result3);

        // 4. 중복 없이 알파벳 역순으로 정렬
        List<String> input4 = Arrays.asList("java", "stream", "java", "api");

        List<String> result4 = input4.stream()
                .distinct()
                //.sorted(Comparator.reverseOrder())
                //.sorted(Comparator.comparing((String s) -> s).reversed())
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());

        System.out.println(result4);


        // 5. 객체 리스트에서 조건 만족하는 ID만 뽑기
        List<User> input5 = List.of(
                new User(1, true),
                new User(2, false),
                new User(3, true)
        );

        List<Integer> result5 = input5.stream()
                .filter((User u) -> u.active)
                .map((User u) -> u.id)
                .collect(Collectors.toList());

        System.out.println(result5);

        // 10. 2차원 배열을 평탄화(flatMap) 후 정렬
        List<List<Integer>> matrix = Arrays.asList(
                Arrays.asList(3, 2),
                Arrays.asList(1, 4)
        );

        List<Integer> list = matrix.stream()
                .flatMap((List<Integer> l) -> l.stream())
                .sorted()
                .collect(Collectors.toList());

        System.out.println(list);
    }
}
