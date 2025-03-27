package streak.d20250327.b10824_네_수;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();
        String c = sc.next();
        String d = sc.next();

        String e = a + b;
        String f = c + d;

        System.out.println(Long.valueOf(e) + Long.valueOf(f));
    }
}
