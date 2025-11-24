package study_archive.week4.b2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N-1;
        int leftVal = arr[left];
        int rightVal = arr[right];
        int minVal = abs(leftVal+rightVal);

        while(left<right){
            int val = arr[left] + arr[right];

            if(abs(val) < minVal){
                leftVal = arr[left];
                rightVal = arr[right];
                minVal = abs(val);
            }

            if(val==0){
                break;
            } else if(val>0){
                right--;
            } else{
                left++;
            }

        }

        System.out.println(leftVal+" "+rightVal);
    }
}
