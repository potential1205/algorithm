package lecture_ssafy.day20241014.s5653_줄기세포배양;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T, N, M, K, answer;
    static List<Cell> cellList;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Cell {
        int y, x, hp, state;

        Cell(int y, int x, int t, int hp, int state) {
            this.y = y;
            this.x = x;
            this.hp = hp;
            this.state = state; // -1 사망, 0 비활성, 1 활성
        }
    }

    static void updateCellState() {

    }

    static void solution(int tc) {
        answer = 0;
        for (int time = 1; time <= K; time++) {
            // 세포 상태 최산화
            updateCellState();

            //
            cellCulture(time);
        }

        System.out.println("#" + tc + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cellList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());

                for (int j = 0; j < M; j++) {
                    int hp = Integer.parseInt(st.nextToken());
                    cellList.add(new Cell(i, j, 0, hp, 0));
                }
            }

            solution(tc);
        }
    }
}
