package study_archive.week9.b9205_맥주_마시면서_걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T,N;
    static Point[] points;
    static Point start,end;
    static boolean[] visit;

    static class Point{
        int y,x;

        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static void solution(){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            Point p = queue.poll();

            if(Math.abs(p.y-end.y) + Math.abs(p.x-end.x)<=1000){
                System.out.println("happy");
                return;
            }

            for(int i=0; i<N; i++){
                if(visit[i]) continue;

                Point nextP = points[i];

                int dist = Math.abs(p.y-nextP.y) + Math.abs(p.x-nextP.x);

                if(dist<=1000){
                    queue.offer(nextP);
                    visit[i] = true;
                }
            }
        }

        System.out.println("sad");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            points = new Point[N];
            visit = new boolean[N];

            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            start = new Point(y,x);

            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                y = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                points[i] = new Point(y,x);
            }

            st = new StringTokenizer(bf.readLine());
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            end = new Point(y,x);

            solution();
        }
    }
}
