package study_archive.week7.s1244_최대_상금;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int answer; // 숫자 N의 각 자리수를 M번 교환 후 받을 수 있는 최대 금액
    static int T,N,length;
    static int[] arr;

    static void swap(int idx1, int idx2){
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    static void search(int depth){
        if(depth==N){
            int result = 0;
            for(int i=0; i<length; i++){
                result += (Math.pow(10,i) * arr[length - i - 1]);
            }

            answer = Math.max(answer,result);
            return;
        }

        for(int i=0; i<length; i++){
            for(int j=i+1; j<length; j++){
                swap(i,j);
                search(depth+1);
                swap(i,j);
            }
        }
    }

    static void solution(int t){
        answer = 0;
        search(0);
        System.out.println("#" + t + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            String input = st.nextToken();
            N = Integer.parseInt(st.nextToken());

            length = input.length();
            arr = new int[length];
            for(int i=0; i<length; i++){
                arr[i] = input.charAt(i) - '0';
            }

            if (length < N) {
                N = arr.length;
            }
            solution(t);
        }
    }
}
