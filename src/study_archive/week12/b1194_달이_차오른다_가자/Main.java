package study_archive.week12.b1194_달이_차오른다_가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    실수했던 부분
    - 열쇠를 먹은 후 해당 칸을 . 으로 변경하면 이후에 방문할 때 영향을 받으므로 board를 원본 상태로 유지해야함
    - 어떤 키를 방문했을 때
        - 이미 해당 키를 가지고 있는 경우 => 키 추가 없이 다음 칸으로 이동
        - 해당 키를 가지고 있지 않은 경우 => 새롭게 키 추가 후 다음 칸으로 이동
      를 모두 고려해야 함.
*/

public class Main {
    static char[][] board;
    static int N,M,answer;
    static Point start;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Point{
        int y, x, cnt, keyNum;

        public Point(int y, int x, int cnt, int keyNum) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.keyNum = keyNum; // 키를 가지고 있는 상태를 표현 2^6
        }
    }

    static int bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        boolean[][][] visit = new boolean[N][M][64];
        visit[start.y][start.x][0] = true;
        board[start.y][start.x] = '.';

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            if(board[cur.y][cur.x]=='1') {
                return cur.cnt;
            }

            for(int i=0; i<4; i++){
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if(ny<0 || nx<0 || ny>=N || nx>=M || visit[ny][nx][cur.keyNum] || board[ny][nx]=='#') {
                    continue;
                }

                if('A' <= board[ny][nx] && board[ny][nx] <= 'F'){
                    int keyIndex = 1 << (5-((int) board[ny][nx]-65));

                    if((cur.keyNum & keyIndex) != 0){
                        queue.offer(new Point(ny, nx, cur.cnt+1, cur.keyNum));
                        visit[ny][nx][cur.keyNum] = true;
                    }

                } else if('a' <= board[ny][nx] && board[ny][nx] <= 'f'){
                    int keyIndex = 1 << (5-((int) board[ny][nx]-97));

                    if((cur.keyNum & keyIndex) == 0){
                        int num = (int) Math.pow(2, 5-((int) board[ny][nx] - 97));

                        queue.offer(new Point(ny, nx, cur.cnt+1, cur.keyNum + num));
                        visit[ny][nx][cur.keyNum + num] = true;

                    } else{
                        queue.offer(new Point(ny, nx, cur.cnt+1, cur.keyNum ));
                        visit[ny][nx][cur.keyNum] = true;
                    }

                } else if(board[ny][nx] == '.' || board[ny][nx] == '1'){
                    queue.offer(new Point(ny, nx, cur.cnt+1, cur.keyNum));
                    visit[ny][nx][cur.keyNum] = true;
                }
            }
        }

        return -1;
    }

    static void solution(){
        answer = bfs();
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for(int i=0; i<N; i++){
            String line = bf.readLine();

            for(int j=0; j<M; j++){
                board[i][j] = line.charAt(j);

                if(board[i][j]=='0') {
                    start = new Point(i,j,0,0);
                }
            }
        }

        solution();
    }
}
