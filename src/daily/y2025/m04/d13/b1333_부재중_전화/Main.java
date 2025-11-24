package daily.y2025.m04.d13.b1333_부재중_전화;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Boolean> list = new ArrayList<>();

        int n = sc.nextInt();
        int l = sc.nextInt();
        int d = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                list.add(true);
            }

            for (int j = 0; j < 5; j++) {
                list.add(false);
            }
        }

        int answer = 0;

        while (answer < list.size()) {
            if (!list.get(answer)) {
                break;
            }

            answer += d;
        }

        System.out.println(answer);
    }
}
