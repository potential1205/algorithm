package study_archive.week13.b17472_다리_만들기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer, landCnt;
    static int[][] board, numBoard, dist;
    static List<List<Point>> pointList;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Edge implements Comparable<Edge> {
        int num, cost;

        Edge(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static List<Point> connect(int sy, int sx, int landNum) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sy, sx));
        numBoard[sy][sx] = landNum;

        List<Point> localPointList = new ArrayList<>();

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            localPointList.add(p);

            for (int i = 0; i < 4; i++) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                if (numBoard[ny][nx] == 0 && board[ny][nx] == 1) {
                    queue.offer(new Point(ny, nx));
                    numBoard[ny][nx] = landNum;
                }
            }
        }

        return localPointList;
    }

    static void prim() {
        List<Edge>[] edgeList = new ArrayList[landCnt+1];

        for (int i = 1; i <= landCnt; i++){
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= landCnt; i++) {
            for (int j = 1; j <= landCnt; j++) {
                if (dist[i][j]!=Integer.MAX_VALUE){
                    edgeList[i].add(new Edge(j, dist[i][j]));
                }
            }
        }

        int visitCnt = 0;
        boolean[] visit = new boolean[landCnt+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));

        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if (visit[edge.num]) continue;;
            visit[edge.num] = true;
            visitCnt++;
            answer += edge.cost;

            for(Edge next : edgeList[edge.num]){
                if(visit[next.num]) continue;
                pq.offer(next);
            }
        }

        if(visitCnt==landCnt){
            System.out.println(answer);
        } else{
            System.out.println(-1);
        }
    }

    static void game() {

        // 영역별로 번호 부여하고 각 영역을 구성하는 point를 저장
        pointList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (numBoard[i][j] == 0 && board[i][j] == 1) {
                    pointList.add(connect(i, j, ++landCnt));
                }
            }
        }

        // 영역별 최소 거리를 저장할 배열 dist
        dist = new int[landCnt+1][landCnt+1];

        for (int i = 1; i <= landCnt; i++) {
            for (int j = 1; j <= landCnt; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i <= landCnt; i++) { // 각 땅의 모든 점으로 부터 사방으로 탐색
            for (Point p : pointList.get(i-1)) {
                for (int d = 0; d < 4; d++) {
                    int ny = p.y;
                    int nx = p.x;
                    int cnt = 0;

                    while(true) {
                        ny = ny + dy[d];
                        nx = nx + dx[d];

                        if (ny < 0 || nx < 0 || ny>=N || nx>=M) break;

                        if (numBoard[ny][nx]==numBoard[p.y][p.x]) break;

                        if (numBoard[ny][nx]!=0 && cnt>1) {
                            dist[i][numBoard[ny][nx]] = Math.min(dist[i][numBoard[ny][nx]], cnt);
                            break;
                        } else if (numBoard[ny][nx]!=0 && cnt<=1) {
                            break;
                        }

                        cnt++;
                    }
                }

            }
        }

        // mst 구하기
        prim();
    }

    static void solution() {
        answer = 0;
        game();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        numBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }
}
