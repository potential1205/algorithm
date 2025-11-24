package daily.y2025.m02.d13.b10768_특별한_날;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int d = sc.nextInt();

        if (m > 2) {
            System.out.println("After");
        } else if (m == 2) {
            if (d == 18) {
                System.out.println("Special");
            } else if (d > 18) {
                System.out.println("After");
            } else if (d < 18) {
                System.out.println("Before");
            }
        } else if (m < 2) {
            System.out.println("Before");
        }
    }
}
