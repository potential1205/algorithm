package algorithm_study.week5.s2382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,M,K;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static long answer;
    static ArrayList<Cell>[][] cellArr;
    static List<Cell> cells;

    static class Cell{
        int y;
        int x;
        int cnt;
        int d;

        Cell(int y, int x, int cnt, int d){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.d = d;
        }
    }

    static int turn(int d){
        if(d==1) return 2;
        else if(d==2) return 1;
        else if(d==3) return 4;
        else return 3;
    }

    static void merge(){
        answer = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){

                if(cellArr[i][j].size()>=1){
                    int maxCnt = 0;
                    int d = 0;
                    int cumCell = 0;
                    int size = cellArr[i][j].size();

                    for(int k=0; k<size; k++){
                        Cell cell = cellArr[i][j].remove(0);
                        if(maxCnt<cell.cnt){
                            maxCnt = cell.cnt;
                            d = cell.d;
                        }
                        cumCell += cell.cnt;
                    }

                    Cell newCell = new Cell(i,j,cumCell,d);
                    cells.add(newCell);
                    answer += cumCell;

                }
            }
        }
    }

    static void move(){

        int size = cells.size();

        for(int i=0; i<size; i++){
            Cell cell = cells.remove(0);

            int ny = cell.y + dy[cell.d-1];
            int nx = cell.x + dx[cell.d-1];

            if(ny<0 || nx<0 || ny>=N || nx>=N) continue;

            cell.y = ny;
            cell.x = nx;

            if(ny==0 || ny==N-1 || nx==0 || nx==N-1){ // 약품이 있는 위치인 경우
                cell.cnt = cell.cnt/2;

                if(cell.cnt!=0){
                    cell.d = turn(cell.d);
                    cellArr[cell.y][cell.x].add(cell);
                }
            } else{
                cellArr[cell.y][cell.x].add(cell);
            }
        }
    }

    static void solution(){
        answer = 0;

        for(int i=0; i<M; i++){
            move();
            merge();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cellArr = new ArrayList[N][N];
            cells = new ArrayList<>();

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    cellArr[i][j] = new ArrayList<>();
                }
            }

            for(int i=0; i<K; i++){
                st = new StringTokenizer(bf.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                Cell newCell = new Cell(y,x,cnt,d);
                cells.add(newCell);
            }

            solution();
            System.out.println("#" + t + " " + answer);
        }
    }
}