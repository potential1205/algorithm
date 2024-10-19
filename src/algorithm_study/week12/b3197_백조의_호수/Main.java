package algorithm_study.week12.b3197_백조의_호수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, answer;
    static char[][] board;
    static Point startPoint, endPoint;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Point {
        int y, x;

        Point(int  y, int x) {
            this.y = y;
            this.x = x;
        }
    }


    static void solution() {
        answer = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();

            for (int j = 0 ; j < M; j++) {
                board[i][j] = line.charAt(j);

                if (board[i][j] == 'L') {
                    if (startPoint == null) {
                        startPoint = new Point(i,j);
                    } else {
                        endPoint = new Point(i,j);
                    }

                    board[i][j] = '.';
                }

                if (board[i][j] == '.') {
                    
                }
            }
        }

        solution();
    }
}
