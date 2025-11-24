package study_archive.week11.b9935_문자열_폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        String target = bf.readLine();

        int N = str.length();
        int M = target.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            result.append(str.charAt(i));

            if (result.length() >= M) {
                boolean isMatch = true;
                for (int j = 0; j < M; j++) {
                    if (result.charAt(result.length() - M + j) != target.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }

                if (isMatch) {
                    result.setLength(result.length() - M);
                }
            }
        }
        
        if (result.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(result);
        }
    }
}
