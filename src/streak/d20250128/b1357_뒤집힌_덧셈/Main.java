package streak.d20250128.b1357_뒤집힌_덧셈;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuffer buffer1 = new StringBuffer(sc.next());
        StringBuffer buffer2 = new StringBuffer(sc.next());

        buffer1.reverse();
        buffer2.reverse();

        int num = Integer.valueOf(buffer1.toString()) + Integer.valueOf(buffer2.toString());

        StringBuffer buffer3 = new StringBuffer(String.valueOf(num));

        buffer3.reverse();

        System.out.println(Integer.valueOf(buffer3.toString()));
    }
}
