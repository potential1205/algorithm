package study_archive.week4.b2110;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N,C;
    static int[] wifi;
    static int answer;

    static void solution(){
        int start = 0;
        int end = wifi[N-1] - wifi[0];

        while(start <= end){
            int mid = (start+end)/2;

            int cnt = 1;
            int std = wifi[0];
            int index = 1;

            while(index<N){
                std = std + mid;

                while(index<N){
                    if(std<=wifi[index]){
                        std = wifi[index];
                        cnt++;
                        index++;
                        break;
                    }
                    index++;
                }
            }

            if(cnt<C){
                end = mid-1;
            } else if(cnt>=C){
                answer = Math.max(answer,mid);
                start = mid+1;
            }
        }

        answer = (start+end)/2;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        C = sc.nextInt();

        wifi = new int[N];

        for(int i=0; i<N; i++){
            wifi[i] = sc.nextInt();
        }

        Arrays.sort(wifi);
        solution();
        System.out.println(answer);
    }
}
