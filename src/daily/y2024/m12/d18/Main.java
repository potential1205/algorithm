package daily.y2024.m12.d18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int answer = 0;

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();

            HashSet<Character> set = new HashSet<>();
            boolean isGroupWord = true;

            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);

                if (set.contains(ch) && str.charAt(j-1) != ch) {
                    isGroupWord = false;
                    break;
                }

                set.add(ch);
            }

            if (isGroupWord) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
