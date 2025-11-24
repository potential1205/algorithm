package daily.y2025.m01.d21.b22864_피로도;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attack = sc.nextInt();
        int work = sc.nextInt();
        int rest = sc.nextInt();
        int maxHp = sc.nextInt();

        int answer = 0;
        int hp = 0;

        if (maxHp < attack) {
            System.out.println(0);
        } else {
            for (int t = 1; t <= 24; t++) {
                if (hp+attack > maxHp) {
                    hp -= rest;
                    hp = Math.max(hp, 0);
                } else {
                    hp = hp + attack;
                    answer = answer + work;
                }
            }

            System.out.println(answer);
        }

    }
}
