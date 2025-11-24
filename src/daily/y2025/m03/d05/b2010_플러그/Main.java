package daily.y2025.m03.d05.b2010_플러그;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());

        int answer = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            answer += Integer.parseInt(st.nextToken());
        }

        answer = answer - n + 1;

        System.out.println(answer);
    }
}
