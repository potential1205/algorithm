package daily.y2025.m01.d23.b1789_수들의_합;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long s = sc.nextLong();

        long i = 1;

        while (true) {
            if (i*(i+1) > s*2) {
                break;
            } else if (i*(i+1) == s*2) {
                i++;
                break;
            } else {
                i++;
            }
        }

        System.out.println(i-1);
    }
}
