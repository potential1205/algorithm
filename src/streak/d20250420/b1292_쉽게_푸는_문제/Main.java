package streak.d20250420.b1292_쉽게_푸는_문제;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int[] arr = new int[1000];

        int value = 1;
        int index = 0;

        while(index < 1000) {
            for (int i = 0; i < value; i++) {
                if (index >= 1000) {
                    break;
                }
                arr[index] = value;
                index++;
            }

            value++;
        }

        int answer = 0;
        for (int i = a; i <= b; i++) {
            answer += arr[i-1];
        }

        System.out.println(answer);
    }
}
