package streak.d20250415.b1550_16진수;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String num = sc.next();

        System.out.println(Integer.parseInt(num, 16));
    }
}
