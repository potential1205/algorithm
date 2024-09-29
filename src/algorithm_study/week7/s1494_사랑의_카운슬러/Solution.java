package algorithm_study.week7.s1494_사랑의_카운슬러;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T,N;
    static long[][] info;

    static void solution(){

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            info = new long[N][2];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(bf.readLine());
                info[i][0] = Long.parseLong(st.nextToken());
                info[i][1] = Long.parseLong(st.nextToken());
            }

            solution();
        }
    }
}
