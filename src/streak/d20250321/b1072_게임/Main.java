package streak.d20250321.b1072_게임;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();

        int ratio = (int) ((long) y * 100 / x);

        if (ratio >= 99) {
            System.out.println(-1);
            return;
        }

        int start = 0;
        int end = 1000000000;
        int answer = -1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int newRatio = (int) ((long) (y + mid) * 100 / (x + mid));

            if (ratio >= newRatio) {
                answer = mid + 1;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
