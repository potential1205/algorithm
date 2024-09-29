package samsung_B.week1.이진수표현;

import java.util.Scanner;

public class Solution {

    static int T;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int filter = (1<<N)-1;

            if((filter & M) == filter){
                System.out.println("#" + t + " ON");
            } else{
                System.out.println("#" + t + " OFF");
            }
        }
    }
}
