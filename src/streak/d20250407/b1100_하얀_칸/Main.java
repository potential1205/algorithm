package streak.d20250407.b1100_하얀_칸;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;

        for (int i = 0; i < 8; i++) {
            String str = br.readLine();

            for (int j = 0; j < 8; j++) {

                if ((i + j) % 2 == 0 && str.charAt(j) == 'F') {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
