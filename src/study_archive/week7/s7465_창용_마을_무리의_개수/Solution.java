package study_archive.week7.s7465_창용_마을_무리의_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,M,answer;
    static int[] p;

    static void makeSet(){
        p = new int[N+1];
        Arrays.fill(p,-1);
    }

    static int findSet(int a){
        if(p[a]<0) return a;
        return p[a] = findSet(p[a]);
    }

    static boolean unionSet(int a, int b){
        int rootA = findSet(a);
        int rootB = findSet(b);

        if(rootA==rootB) return false;

        int sizeA = Math.abs(p[rootA]);
        int sizeB = Math.abs(p[rootB]);

        if(sizeA > sizeB){
            p[rootA] += p[rootB];
            p[rootB] = rootA;
        } else{
            p[rootB] += p[rootA];
            p[rootA] = rootB;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t = 1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            makeSet();

            for(int i=0; i<M; i++){
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                unionSet(a,b);
            }

            answer = 0;
            for(int i=1; i<=N; i++){
                if(p[i]<0) answer++;
            }

            System.out.println("#" + t + " " + answer);
        }

    }
}
