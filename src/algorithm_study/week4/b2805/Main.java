package algorithm_study.week4.b2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N,M;
    static long answer;

    static void solution(){
        int start = 0;
        int end = arr[N-1];

        while(start<=end){
            int mid = (start+end)/2; // 절단기 높이

            long cum = 0; // 가져갈 수 있는 나무 높이 합
            for(int i=0; i<N; i++){
                if(arr[i]>mid){
                    cum += (arr[i]-mid);
                }
            }

            if(cum == M){
                answer = Math.max(answer, mid);
                break;
            } else if(cum > M){ // 절단기가 너무 낮은 경우
                start = mid+1;
                answer = mid;
            } else{ // 절단기가 너무 높은 경우
                end = mid-1;
            }
        }

        System.out.println(answer);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solution();
    }
}
