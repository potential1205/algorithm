package daily.y2025.m09.d08.p60060_가사_검색;

import java.util.*;

// 이분탐색
class Solution2 {
    public int[] solution(String[] words, String[] queries) {
        // 1) 길이별로 단어를 분류: 정방향/역방향 정렬 리스트
        Map<Integer, List<String>> forward = new HashMap<>();
        Map<Integer, List<String>> backward = new HashMap<>();
        for (String w : words) {
            int n = w.length();
            forward.computeIfAbsent(n, k -> new ArrayList<>()).add(w);
            backward.computeIfAbsent(n, k -> new ArrayList<>()).add(new StringBuilder(w).reverse().toString());
        }
        for (List<String> list : forward.values()) Collections.sort(list);
        for (List<String> list : backward.values()) Collections.sort(list);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            int n = q.length();

            List<String> base; // 탐색 대상 리스트
            String keyMin, keyMax;

            if (!forward.containsKey(n)) { // 해당 길이 단어 없음
                ans[i] = 0;
                continue;
            }

            if (q.charAt(0) != '?') {
                // 접두 고정 (정방향)
                base = forward.get(n);
                String prefix = q.substring(0, q.indexOf('?')); // 첫 ?前까지
                keyMin = prefix + repeat('a', n - prefix.length());
                keyMax = prefix + repeat('z', n - prefix.length());
            } else {
                // 접미 고정 (역방향)
                base = backward.get(n);
                String rq = new StringBuilder(q).reverse().toString();
                String prefix = rq.substring(0, rq.indexOf('?'));
                keyMin = prefix + repeat('a', n - prefix.length());
                keyMax = prefix + repeat('z', n - prefix.length());
            }

            ans[i] = upperBound(base, keyMax) - lowerBound(base, keyMin);
        }
        return ans;
    }

    private static int lowerBound(List<String> a, String key) {
        int l = 0, r = a.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a.get(m).compareTo(key) >= 0) r = m;
            else l = m + 1;
        }
        return l;
    }

    private static int upperBound(List<String> a, String key) {
        int l = 0, r = a.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a.get(m).compareTo(key) > 0) r = m;
            else l = m + 1;
        }
        return l;
    }

    private static String repeat(char ch, int cnt) {
        char[] arr = new char[cnt];
        Arrays.fill(arr, ch);
        return new String(arr);
    }
}
