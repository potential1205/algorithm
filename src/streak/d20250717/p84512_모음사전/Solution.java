package streak.d20250717.p84512_모음사전;

import java.util.*;

class Solution {

    static List<String> list = new ArrayList<>();

    static String[] strArr = {"A", "E", "I", "O", "U"};

    static void permutation(int size, int depth, String path) {
        if (depth == size) {
            list.add(path);
            return;
        }

        for (int i = 0; i < 5; i++) {
            permutation(size, depth + 1, path + strArr[i]);
        }
    }

    public int solution(String word) {
        int answer = 0;


        for (int i = 1; i <= 5; i++) {
            permutation(i, 0, "");
        }

        Collections.sort(list);

        answer = list.indexOf(word);

        return answer + 1;
    }
}