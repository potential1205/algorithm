package study_archive.week8.b13460_구슬_탈출_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M,answer;
    static char[][] board;
    static Beads beads;

    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    static class Beads{
        int ry,rx,by,bx,cnt;

        public Beads(int ry, int rx, int by, int bx, int cnt) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.cnt = cnt;
        }
    }

    static int bfs(){
        Queue<Beads> queue = new LinkedList<>();
        queue.offer(beads);

        while(!queue.isEmpty()){
            Beads b = queue.poll();

            if(b.cnt==10) continue;

            for(int d=0; d<4; d++){
                int ry = b.ry;
                int rx = b.rx;
                int by = b.by;
                int bx = b.bx;
                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 공 이동
                while(true){
                    int nextRy = ry + dy[d];
                    int nextRx = rx + dx[d];

                    if(board[nextRy][nextRx] == '#') break;

                    if(board[nextRy][nextRx]== 'O'){
                        isRedHole = true;
                        break;
                    }

                    ry = nextRy;
                    rx = nextRx;
                }

                // 파란 공 이동
                while(true){
                    int nextBy = by + dy[d];
                    int nextBx = bx + dx[d];

                    if(board[nextBy][nextBx] == '#') break;

                    if(board[nextBy][nextBx]== 'O'){
                        isBlueHole = true;
                        break;
                    }

                    by = nextBy;
                    bx = nextBx;
                }

                if(isBlueHole) continue;
                else if(isRedHole) {
                    return b.cnt + 1;
                }

                if(b.ry == ry && b.rx == rx && b.by == by && b.bx == bx) continue;

                if(ry == by && rx == bx){
                    if(d==0){
                        if(b.ry < b.by){
                            by++;
                        } else{
                            ry++;
                        }
                    } else if(d==1){
                        if(b.ry < b.by){
                            ry--;
                        } else{
                            by--;
                        }
                    } else if(d==2){
                        if(b.rx < b.bx){
                            bx++;
                        } else{
                            rx++;
                        }
                    } else if(d==3){
                        if(b.rx < b.bx){
                            rx--;
                        } else{
                            bx--;
                        }
                    }
                }

                queue.offer(new Beads(ry,rx,by,bx,b.cnt+1));
            }
        }

        return -1;
    }

    static void solution(){
        answer = Integer.MAX_VALUE;
        answer = bfs();
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        beads = new Beads(0,0,0,0,0);

        for(int i=0; i<N; i++){
            board[i] = bf.readLine().toCharArray();

            for(int j=0; j<M; j++){
                if(board[i][j]=='R'){
                    beads.ry = i;
                    beads.rx = j;
                    board[i][j] = '.';
                } else if(board[i][j]=='B'){
                    beads.by = i;
                    beads.bx = j;
                    board[i][j] = '.';
                }
            }
        }

        solution();
    }
}
