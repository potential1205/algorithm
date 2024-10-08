package battle_ssafy.s1226_미로1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int T,answer;
    static int[][] board;
    static Point start, end;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    static class Point{
        int y,x;

        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static int bfs(){
        boolean[][] visit = new boolean[16][16];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visit[start.y][start.x] = true;

        while(!queue.isEmpty()){
            Point curPoint = queue.poll();

            if(curPoint.y == end.y && curPoint.x == end.x) return 1;

            for(int i=0; i<4; i++){
                int ny = curPoint.y + dy[i];
                int nx = curPoint.x + dx[i];

                if(ny<0 || nx<0 || ny>=16 || nx>=16 || visit[ny][nx] || board[ny][nx]==1) continue;

                queue.offer(new Point(ny,nx));
                visit[ny][nx] = true;
            }
        }

        return 0;
    }

    static void solution(int t){
        answer = bfs();
        System.out.println("#" + t + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t=1; t<=10; t++){
            st = new StringTokenizer(bf.readLine());
            T = Integer.parseInt(st.nextToken());

            board = new int[16][16];
            for(int i=0; i<16; i++){
                String line =  bf.readLine();

                for(int j=0; j<16; j++){
                    board[i][j] = line.charAt(j) - '0';

                    if(board[i][j]==2) start = new Point(i,j);
                    else if(board[i][j]==3) end = new Point(i,j);
                }
            }

            solution(t);
        }
    }
}
