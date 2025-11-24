package daily.y2025.m02.d05.b3003_킹_퀸_룩_비숍_나이트_폰;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[] arr1 = {1,1,2,2,2,8};
        int[] arr2 = new int[6];

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            arr2[i] = arr1[i] - sc.nextInt();
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(arr2[i] + " ");
        }
    }
}
