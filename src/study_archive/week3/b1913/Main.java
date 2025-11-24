package study_archive.week3.b1913;

import java.util.Scanner;

public class Main {
    static int N,M;
    static int[][] board;
    static int y,x;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        int sy = N/2;
        int sx = N/2;
        int cnt = 1;

        board = new int[N][N];

        board[sy][sx] = cnt;
        cnt+=1;

        int massy = 1;
        int massx = 1;
        int dy = -1;
        int dx = 1;

        for(int i=1; i<2*N; i++){
            if(i%2!=0){
                if(i==2*N-1){
                    massy-=1;
                }
                for(int j=0; j<massy; j++){
                    sy = sy + dy;
                    board[sy][sx] = cnt;
                    cnt++;
                }
                dy*=-1;
                massy++;
            } else {
                for(int j=0; j<massx; j++){
                    sx = sx + dx;
                    board[sy][sx] = cnt;
                    cnt++;
                }
                dx*=-1;
                massx++;
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(board[i][j] + " ");
                if(board[i][j] == M){
                    y=i+1;
                    x=j+1;
                }
            }
            System.out.println();
        }
        System.out.println(y+" "+x);
    }
}
