package study_archive.week3.b14719;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int H,W;
    static int[] blocks;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        H = sc.nextInt();
        W = sc.nextInt();

        blocks = new int[W];

        for(int i=0; i<W; i++){
            blocks[i] = sc.nextInt();
        }
        int answer = 0;

        for(int i=1; i<W; i++) {
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();

            for(int j=0; j<i; j++){
                left.add(blocks[j]);
            }

            for(int j=i; j<W; j++){
                right.add(blocks[j]);
            }

            int leftMax = Collections.max(left);
            int rightMax = Collections.max(right);
            int std = Math.min(leftMax,rightMax);

            if(std>blocks[i]){
                answer += (std-blocks[i]);
            }
        }
        System.out.println(answer);
    }

}
