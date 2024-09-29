package samsung_B.week1.새로운불면증치료법;

import java.util.Scanner;

public class Solution {

    static int T,N,answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for(int t=1; t<=T; t++){
            N = sc.nextInt();

            answer = 0;
            int target = (1<<10)-1;
            int visit = 0;

            while(target!=visit){
                answer += N;
                char[] chars = String.valueOf(answer).toCharArray();
                for(char c : chars){
                    int num = c-'0';
                    visit = visit | (1<<num);
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }
}
