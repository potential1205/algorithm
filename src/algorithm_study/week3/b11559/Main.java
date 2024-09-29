package algorithm_study.week3.b11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static char[][] board = new char[12][6];
    static int answer;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static boolean flag;
    static boolean[][] visit;

    static class State{
        int y;
        int x;
        char c;
        State(int y, int x, char c){
            this.y=y;
            this.x=x;
            this.c=c;
        }
    }

    static class Info{
        int y;
        int x;
        Info(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static void lineDown(int j) {
        Queue<State> queue = new LinkedList<>();
        int idx = 11;

        for(int i=11; i>=0; i--) {
            if(board[i][j] != '.') {
                queue.add(new State(i, j, board[i][j]));
                board[i][j] = '.';
            }
        }
        while(!queue.isEmpty()) {
            State p = queue.poll();
            board[idx][j] = p.c;
            idx--;
        }

    }

    static void down(){
        for(int i=0; i<6; i++){
            lineDown(i);
        }
    }

    static boolean[][] bfs(boolean[][] visit, int y, int x, char c){
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(y,x,c));

        List<Info> store = new LinkedList<>();
        store.add(new Info(y,x));
        int cnt = 1;

        while(!queue.isEmpty()){
            State s = queue.poll();
            visit[s.y][s.x]=true;

            for(int i=0; i<4; i++){
                int ky = s.y + dy[i];
                int kx = s.x + dx[i];
                if(ky<0 || kx<0 || ky>=12 || kx>=6 || visit[ky][kx]){
                    continue;
                }

                char color = board[ky][kx];

                if(s.c==color){
                    queue.offer(new State(ky,kx,color));
                    visit[ky][kx]=true;
                    store.add(new Info(ky,kx));
                    cnt+=1;
                }
            }
        }

        if(cnt>=4){
            for(int i=0; i<store.size(); i++){
                Info info = store.get(i);
                board[info.y][info.x] = '.';
            }
            flag=true;
        }

        return visit;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<12; i++){
            String line = bf.readLine();
            for(int j=0; j<6; j++){
                board[i][j] = line.charAt(j);
            }
        }

        while(true){
            flag=false;
            visit = new boolean[12][6];
            for(int i=0; i<12; i++){
                for(int j=0; j<6; j++){
                    if(board[i][j]!='.' && !visit[i][j]){
                        visit = bfs(visit, i,j, board[i][j]);
                    }
                }
            }
            down();
            if(!flag){
                break;
            } else{
                answer++;
            }
        }

        System.out.println(answer);

    }
}
