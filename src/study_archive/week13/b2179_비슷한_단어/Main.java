package study_archive.week13.b2179_비슷한_단어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, maxLen;
    static List<String> strList;
    static List<Integer> strLen;
    static String source, target;

    static void solution() {
        for (int i = 0; i < N-1; i++) {
            String str1 = strList.get(i);

            for (int j = i+1; j < N; j++) {
                String str2 = strList.get(j);

                if (str1.equals(str2)) continue;

                int minLen = Math.min(strLen.get(i), strLen.get(j));
                int sameLen = 0;

                for (int k = 0; k < minLen; k++) {
                    if (str1.charAt(k) != str2.charAt(k)) break;
                    sameLen++;
                }

                if (maxLen < sameLen) {
                    maxLen = sameLen;
                    source = str1;
                    target = str2;
                }
            }
        }

        System.out.println(source);
        System.out.println(target);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        strList = new ArrayList<>();
        strLen = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();

            strList.add(str);
            strLen.add(str.length());
        }

        solution();
    }
}
