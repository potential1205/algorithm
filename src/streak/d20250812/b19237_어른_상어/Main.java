package streak.d20250812.b19237_어른_상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, k;
    static List[][] tempBoard;
    static Shark[][] sharkBoard;
    static Smell[][] smellBoard;
    static int[][][] directionPriority;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Shark implements Comparable<Shark> {
        int id;
        int d;

        Shark(int id, int d) {
            this.id = id;
            this.d = d;
        }

        @Override
        public int compareTo(Shark o) {
            return this.id - o.id;
        }
    }

    static class Smell {
        int id;
        int hp;

        Smell(int id, int hp) {
            this.id = id;
            this.hp = hp;
        }
    }

    static void scatterSmell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sharkBoard[i][j] == null) continue;
                smellBoard[i][j] = new Smell(sharkBoard[i][j].id, k);
            }
        }
    }

    static boolean checkEmptySmell(int y, int x) {
        int id = sharkBoard[y][x].id;
        int d = sharkBoard[y][x].d;

        for (int i = 0; i < 4; i++) {
            int nextD = directionPriority[id - 1][d][i];
            int ny = y + dy[nextD];
            int nx = x + dx[nextD];

            if (ny < 0 || nx < 0 || ny >= n || nx >= n || smellBoard[ny][nx] != null) continue;

            tempBoard[ny][nx].add(new Shark(id, nextD));
            return true;
        }

        return false;
    }

    static void checkMySmell(int y, int x) {
        int id = sharkBoard[y][x].id;
        int d = sharkBoard[y][x].d;

        for (int i = 0; i < 4; i++) {
            int nextD = directionPriority[id - 1][d][i];
            int ny = y + dy[nextD];
            int nx = x + dx[nextD];

            if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;

            if (smellBoard[ny][nx].id == id) {
                tempBoard[ny][nx].add(new Shark(id, nextD));
                break;
            }
        }
    }

    static void move() {
        tempBoard = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tempBoard[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sharkBoard[i][j] != null) {
                    if (checkEmptySmell(i, j)) {
                    } else {
                        checkMySmell(i, j);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tempBoard[i][j].size() > 0) {
                    Collections.sort(tempBoard[i][j]);
                    sharkBoard[i][j] = (Shark) tempBoard[i][j].get(0);
                } else {
                    sharkBoard[i][j] = null;
                }
            }
        }
    }

    static void downSmell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (smellBoard[i][j] == null) continue;
                smellBoard[i][j].hp--;

                if (smellBoard[i][j].hp == 0) {
                    smellBoard[i][j] = null;
                }
            }
        }
    }

    static int checkShark() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sharkBoard[i][j] == null) continue;
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][n];
        sharkBoard = new Shark[n][n];
        smellBoard = new Smell[n][n];

        // 초기 보드 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 방향 입력
        int[] initDirection = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            initDirection[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 초기 상어 배치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = board[i][j];
                if (val == 0) {
                    sharkBoard[i][j] = null;
                } else {
                    sharkBoard[i][j] = new Shark(val, initDirection[val - 1]);
                }
            }
        }

        // 상어별 방향 우선순위 저장
        directionPriority = new int[m][4][4];
        for (int id = 0; id < m; id++) {
            for (int d = 0; d < 4; d++) {
                st = new StringTokenizer(br.readLine());
                for (int idx = 0; idx < 4; idx++) {
                    int nextD = Integer.parseInt(st.nextToken()) - 1;
                    directionPriority[id][d][idx] = nextD;
                }
            }
        }

        // 시뮬레이션 시작
        int answer = -1;
        scatterSmell();

        for (int t = 0; t < 1000; t++) {
            move();
            downSmell();
            scatterSmell();
            if (checkShark() == 1) {
                answer = t;
                break;
            }
        }

        if (answer == -1) {
            System.out.println(answer);
        } else {
            System.out.println(answer + 1);
        }
    }
}
