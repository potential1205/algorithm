package daily.y2025.m01.d16.b6550_부분_문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            String str = bf.readLine();
            if (str == null) break;

            st = new StringTokenizer(str);

            String s = st.nextToken();
            String t = st.nextToken();

            int idx = 0;

            for (int i = 0; i < t.length(); i++) {
                if (t.charAt(i) == s.charAt(idx)) {
                    idx++;
                }

                if (idx == s.length()) {
                    break;
                }
            }

            if (idx == s.length()) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

        }

    }
}
