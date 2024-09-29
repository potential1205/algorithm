package algorithm_study.week5.s4008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T,N, minVal, maxVal, answer;
    static int[] opes, nums, newOpes;

    static void solution(int cnt, int val){
        if(cnt==N-1){
            minVal = Math.min(minVal, val);
            maxVal = Math.max(maxVal, val);
            return;
        }

        if(opes[0]>newOpes[0]){
            newOpes[0]++;
            solution(cnt + 1, val + nums[cnt + 1]);
            newOpes[0]--;
        }

        if(opes[1]>newOpes[1]){
            newOpes[1]++;
            solution(cnt+1, val-nums[cnt+1]);
            newOpes[1]--;
        }

        if(opes[2]>newOpes[2]){
            newOpes[2]++;
            solution(cnt+1, val*nums[cnt+1]);
            newOpes[2]--;
        }

        if(opes[3]>newOpes[3]){
            newOpes[3]++;
            solution(cnt+1, val/nums[cnt+1]);
            newOpes[3]--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            opes = new int[4];
            newOpes = new int[4];

            st = new StringTokenizer(bf.readLine());
            for(int i=0; i<4; i++){
                opes[i] = Integer.parseInt(st.nextToken());
            }

            nums = new int[N];
            st = new StringTokenizer(bf.readLine());
            for(int i=0; i<N; i++){
                nums[i] = Integer.parseInt(st.nextToken());
            }

            minVal = Integer.MAX_VALUE;
            maxVal = Integer.MIN_VALUE;

            solution(0,nums[0]);
            answer = maxVal - minVal;
            System.out.println("#" + t + " " + answer);
        }

    }
}
