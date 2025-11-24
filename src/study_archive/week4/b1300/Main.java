package study_archive.week4.b1300;

import java.util.Scanner;

public class Main {

    static int N,K;

    static long solution(){

        long start = 1;
        long end = K;

        while(start<end){
            long mid = (start+end)/2;

            long cum = 0;
            for(int i=1; i<=N; i++){
                cum += Math.min(mid/i,N);
            }

            if(cum>=K){
                end = mid;
            } else{
                start = mid+1;
            }
        }

        return (start+end)/2;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        System.out.println(solution());
    }
}
