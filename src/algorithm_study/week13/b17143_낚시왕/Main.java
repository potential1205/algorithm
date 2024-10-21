package algorithm_study.week13.b17143_낚시왕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, M, answer;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1,-1};

    static List<Shark> sharkList;
    static int minY;

    static class Shark implements Comparable<Shark> {
        int y, x, s, d, z;

        Shark(){
        }

        Shark(int y, int x, int s, int d, int z) {
            this.y = y;
            this.x = x;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public int compareTo(Shark o) {
            return o.z - this.z;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "y=" + y +
                    ", x=" + x +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    '}';
        }
    }

    static void game() {
        PriorityQueue<Shark> pq = new PriorityQueue<>();

        for (int time = 0; time < C; time++) {

            // 상어 배치
            boolean[][] visit = new boolean[R][C];

            while (!pq.isEmpty()) {
                Shark s = pq.poll();

                if (!visit[s.y][s.x]) {
                    visit[s.y][s.x] = true;
                    sharkList.add(s);
                }
            }

            Shark selectedShark = new Shark();
            int index = -1;
            minY = R;

            // 상어 사냥
            for (int i = 0; i < sharkList.size(); i++) {
                Shark s = sharkList.get(i);

                if (s.x == time && s.y < minY) {
                    minY = s.y;
                    selectedShark = s;
                    index = i;
                }
            }

            if (index!=-1){
                answer += selectedShark.z;
                sharkList.remove(index);
            }

            // 상어 이동
            for (Shark s : sharkList) {
                int ny = s.y;
                int nx = s.x;

                for (int i = 0; i < s.s; i++) {
                    int ky = ny + dy[s.d];
                    int kx = nx + dx[s.d];

                    if (ky < 0 || kx < 0 || ky >= R || kx >= C) {
                        if (s.d == 0) {
                            s.d = 1;
                        } else if (s.d == 1){
                            s.d = 0;
                        } else if (s.d == 2) {
                            s.d = 3;
                        } else if (s.d == 3) {
                            s.d = 2;
                        }
                    }
                    ny = ny + dy[s.d];
                    nx = nx + dx[s.d];
                }

                pq.offer(new Shark(ny, nx, s.s, s.d, s.z));
            }

            sharkList = new ArrayList<>();
        }
    }

    static void solve() {
        answer = 0;
        game();
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sharkList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());

            sharkList.add(new Shark(y, x, s, d, z));
        }

        solve();
    }
}
