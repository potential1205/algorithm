package algorithm_study.week11.b2632_피자판매;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int target;
    static int N,M;
    static int[] arr1, arr2, sum1, sum2, answer1, answer2;

    static void solution(){
        sum1[1] = arr1[1];
        for(int i=2; i<=2*N; i++){
            sum1[i] = sum1[i-1] + arr1[i];
        }

        sum2[1] = arr2[1];
        for(int i=2; i<=2*M; i++){
            sum2[i] = sum2[i-1] + arr2[i];
        }

        answer1 = new int[target+1];
        for(int i=1; i<N; i++){
            for(int s=1; s<=N; s++){
                int val = sum1[s+i-1] - sum1[s-1];
                if(val>target) continue;
                answer1[val]++;
            }
        }

        if (sum1[N] <= target) answer1[sum1[N]]++;

        answer2 = new int[target+1];
        for(int i=1; i<M; i++){
            for(int s=1; s<=M; s++){
                int val = sum2[s+i-1] - sum2[s-1];
                if(val>target) continue;
                answer2[val]++;
            }
        }

        if (sum2[M] <= target) answer2[sum2[M]]++;

        int answer = 0;
        answer += answer1[target];
        answer += answer2[target];
        for (int i = 1; i < target; i++) {
            int sizeA = i;
            int sizeB = target - sizeA;
            answer += answer1[sizeA] * answer2[sizeB];
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[2*N+1];
        sum1 = new int[2*N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(bf.readLine());
            arr1[i] = Integer.parseInt(st.nextToken());
            arr1[i+N] = arr1[i];
        }

        arr2 = new int[2*M+1];
        sum2 = new int[2*M+1];
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(bf.readLine());
            arr2[i] = Integer.parseInt(st.nextToken());
            arr2[i+M] = arr2[i];
        }

        solution();
    }
}
