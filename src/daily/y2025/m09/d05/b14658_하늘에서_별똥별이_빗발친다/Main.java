package daily.y2025.m09.d05.b14658_하늘에서_별똥별이_빗발친다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

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
        List<Star> starList = new ArrayList<>();
        List<Integer> starY = new ArrayList<>();
        List<Integer> starX = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            starArr[i] = new Star(y, x);
            starY.add(y);
            starX.add(x);
        }

        for (int y : starY) {
            for (int x : starX) {
                starList.add(new Star(y, x));
            }
        }

        int answer = 0;

        for (Star point : starList) {
            int cnt = 0;
            for (int j = 0; j < k; j++) {
                Star star2 = starArr[j];
                if (point.y <= star2.y && star2.y <= point.y + l
                        && point.x <= star2.x && star2.x <= point.x + l) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(k - answer);
    }
}
