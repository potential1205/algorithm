package lecture_ssafy.day20241014.s5653_줄기세포배양;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T, N, M, K, answer;
    static List<Cell> cellList;
    static boolean[][] visit;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    enum CellState {
        INIT, ACTIVE, DEAD;
    }

    static class Cell implements Comparable<Cell>{
        int y, x, hp, changeTime;
        CellState cellState;

        Cell(int y, int x, int hp, int changeTime, CellState cellState) {
            this.y = y;
            this.x = x;
            this.hp = hp;
            this.cellState = cellState;
            this.changeTime = changeTime;
        }

        @Override
        public int compareTo(Cell o) {
            return o.hp - this.hp;
        }
    }

    static void solution(int tc) {
        answer = 0;

        PriorityQueue<Cell> pq = new PriorityQueue<>();

        for (int time = 1; time <= K; time++) {
            while (!pq.isEmpty()) {
                Cell cell = pq.poll();

                if (visit[cell.y][cell.x]) continue;

                cellList.add(cell);
                visit[cell.y][cell.x] = true;
            }

            for (int i = 0; i < cellList.size(); i++) {
                Cell cell = cellList.get(i);

                if (cell.cellState.equals(CellState.DEAD)) continue;

                // 활성 상태로 전환 후 사방으로 세포 번식
                if (cell.cellState.equals(CellState.INIT) && cell.changeTime == time) {
                    cell.cellState = CellState.ACTIVE;
                    cell.changeTime = cell.changeTime + cell.hp;

                    for (int d = 0; d < 4; d++) {
                        int ny = cell.y + dy[d];
                        int nx = cell.x + dx[d];

                        pq.offer(new Cell(ny, nx, cell.hp, cell.changeTime+1, CellState.INIT));
                    }

                } else if (cell.cellState.equals(CellState.ACTIVE) && cell.changeTime == time) {
                    cell.cellState = CellState.DEAD;
                }
            }
        }

        for (int i = 0; i < cellList.size(); i++) {
            Cell cell = cellList.get(i);

            if (cell.cellState.equals(CellState.INIT)
                    || cell.cellState.equals(CellState.ACTIVE)) {
                answer++;
            }
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

            visit = new boolean[N+2*K][M+2*K];
            cellList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());

                for (int j = 0; j < M; j++) {
                    int hp = Integer.parseInt(st.nextToken());

                    if (hp != 0) {
                        cellList.add(new Cell(K+i, K+j, hp, hp, CellState.INIT));
                        visit[K+i][K+j] = true;
                    }
                }
            }

            solution(tc);
        }
    }
}

