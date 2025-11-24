package study_archive.week5.s2115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,M,C,answer;
    static int[][] board;
    static boolean[][] visit;

    static Point p1,p2;

    static int[] honey1, honey2;
    static int maxProfit, maxCumHoney, totalProfit;
    static int cumHoney1, cumHoney2, profit1, profit2;

    static class Point{
        int sy,sx;
        int ey,ex;

        Point(int sy, int sx, int ey, int ex){
            this.sy = sy;
            this.sx = sx;
            this.ey = ey;
            this.ex = ex;
        }
    }

    static void subset(int[] honey, int cnt, int cumHoney, int cumProfit){
        if(cnt==M) return;

        if(cumHoney+honey[cnt]<=C){
            if(maxProfit < cumProfit+(honey[cnt]*honey[cnt])){
                maxCumHoney = cumHoney+honey[cnt];
                maxProfit = cumProfit+(honey[cnt]*honey[cnt]);
            }
            subset(honey, cnt+1, cumHoney+honey[cnt], cumProfit+(honey[cnt]*honey[cnt]));
        }

        if(cumHoney<=C){
            if(maxProfit < cumProfit){
                maxCumHoney = cumHoney;
                maxProfit = cumProfit;
            }
            subset(honey, cnt+1, cumHoney, cumProfit);
        }
    }

    static void collectAllHoney(){
        honey1 = new int[M];
        honey2 = new int[M];

        for(int i=0; i<M; i++){
            honey1[i] = board[p1.sy][p1.sx+i];
            honey2[i] = board[p2.sy][p2.sx+i];
        }
    }

    static void calculateAllHoney(){
        cumHoney1 = cumHoney2 = 0;
        profit1 = profit2 = 0;

        for(int i=0; i<M; i++){
            cumHoney1 += honey1[i];
            profit1 += (honey1[i]*honey1[i]);

            cumHoney2 += honey2[i];
            profit2 += (honey2[i]*honey2[i]);
        }
    }

    static void checkHoney(){
        totalProfit = maxProfit = 0;

        if(cumHoney1>C){ // 꿀의 총량이 C보다 큰 경우 M칸의 꿀 중에 C를 넘지 않으며 최대 수익을 낼 수 있는 부분 집합을 찾아야됨
            subset(honey1,0,0,0);
            totalProfit += maxProfit;
        } else totalProfit += profit1;

        maxProfit = 0;

        if(cumHoney2>C){
            subset(honey2,0,0,0);
            totalProfit += maxProfit;
        } else totalProfit += profit2;
    }

    static void solution(int cnt){
        if(cnt==2){
            if(p1.sy==p2.sy&&p1.ex>=p2.sx) return; // 꿀통이 겹치는 경우

            collectAllHoney(); // 일단 p1, p2에 대해 각각 M만큼 꿀 채집

            calculateAllHoney(); // 채집한 꿀의 총량과 수익 계산

            checkHoney(); // 채집한 꿀이 조건에 부합한지 즉, 꿀의 총량이 <=C 인지 체크

            answer = Math.max(answer, totalProfit);
            return;
        }

        // 꿀을 채집할 위치를 선정함 p1,p2
        for(int i=0; i<N; i++){
            for(int j=0; j<N-M+1; j++){
                if(cnt==0){
                    p1 = new Point(i,j,i,j+M-1);
                } else if(cnt==1){
                    if(i<p1.sy) continue;
                    p2 = new Point(i,j,i,j+M-1);
                }
                solution(cnt+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            visit = new boolean[N][N];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer=0;
            solution(0);
            System.out.println("#" + t + " " + answer);
        }
    }
}
