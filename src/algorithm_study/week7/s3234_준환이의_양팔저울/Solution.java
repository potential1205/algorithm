package algorithm_study.week7.s3234_준환이의_양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int answer;
    static int T,N;
    static int cum;

    static void search(int depth, int left, int right, int[] infos, boolean[] visit){
        if(depth == N){
            answer++;
            return;
        }

        if(left>cum/2){
            answer += (Math.pow(2,N-depth));
            return;
        }

        for(int i=0; i<N; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            search(depth + 1, left + infos[i], right,infos,visit);
            search(depth + 1, left, right+infos[i],infos,visit);
            visit[i] = false;
        }
    }

    static void solution(int t,int[] infos, boolean[] visit){
        search(0,0,0, infos, visit);
        System.out.println("#" + t + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            int[] infos = new int[N];
            boolean[] visit = new boolean[N];
            cum = 0;
            answer = 0;

            st = new StringTokenizer(bf.readLine());
            for(int i=0; i<N; i++){
                infos[i] = Integer.parseInt(st.nextToken());
                cum += infos[i];
            }

            solution(t,infos,visit);
        }
    }
}