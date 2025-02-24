package streak.d20250224.b1388_바닥_장식;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] arr;
    static boolean[][] visit;

    static void dfs(int y, int x, boolean row) {
        visit[y][x] = true;

        if (row) {
            x++;
            if(0 <= x && x < M && arr[y][x] == '-') {
                dfs(y, x, true);
            }
        } else {
            y++;
            if(0 <= y && y<N && arr[y][x] != '-') {
                dfs(y, x, false);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visit[i][j]) continue;

                if(arr[i][j] == '-') {
                    dfs(i, j, true);
                } else {
                    dfs(i, j, false);
                }

                cnt++;
            }
        }

        System.out.println(cnt);

    }
}