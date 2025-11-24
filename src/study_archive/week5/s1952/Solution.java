package study_archive.week5.s1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T, answer;
    static int prices[], months[], monthCosts[];

    static void solution(int month ,int cost) {
        if(month>=12) {
            answer = Math.min(answer, cost);
            answer = Math.min(answer, prices[3]);
            return;
        }

        if(answer>cost+monthCosts[month]) {
            solution(month+1,cost+monthCosts[month]);
        }

        if(answer>cost+prices[2]) {
            solution(month+3,cost+prices[2]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(bf.readLine());
            prices = new int[4];
            for(int i=0; i<4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(bf.readLine());
            months = new int[12];
            for(int i=0; i<12; i++) {
                months[i] = Integer.parseInt(st.nextToken());
            }

            // 1일권 vs 1달권
            monthCosts = new int[12];
            for(int i=0; i<12; i++) {
                int dailyCost = months[i] * prices[0];
                int monthlyCost = prices[1];
                if(dailyCost<monthlyCost) {
                    monthCosts[i] = dailyCost;
                } else {
                    monthCosts[i] = monthlyCost;
                }
            }

            answer = Integer.MAX_VALUE;
            solution(0,0);
            System.out.println("#" + t + " " + answer);
        }

    }
}