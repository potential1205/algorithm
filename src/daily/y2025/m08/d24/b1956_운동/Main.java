package daily.y2025.m08.d24.b1956_운동;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // 시작부터 운동 불가능한 경우
        if (m + T > M) {
            System.out.println(-1);
            return;
        }

        int time = 0;
        int work = 0;
        int cur = m;

        while (work < N) {
            if (cur + T <= M) {
                cur += T;
                work++;
            } else {
                cur -= R;
                if (cur < m) cur = m;
            }
            time++;
        }

        System.out.println(time);
    }
}