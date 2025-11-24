package daily.y2025.m09.d05.b14658_하늘에서_별똥별이_빗발친다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Timeout1 {

    static class Star {
        int y;
        int x;

        Star(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Star[] starArr = new Star[k];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            starArr[i] = new Star(y, x);
        }

        int answer = 0;

        for (int i = 0; i < n - l + 1; i++) {
            for (int j = 0; j < m - l + 1; j++) {
                int cnt = 0;

                for (int a = 0; a < k; a++) {
                    Star star = starArr[a];
                    if (i <= star.y && star.y < i + l && j <= star.x && star.x < j + l) {
                        cnt++;
                    }
                }
                answer = Math.max(answer, cnt);
            }
        }

        System.out.println(answer);
    }
}
