package streak.d20250814.p250137_붕대_감기;

import java.util.*;

class Solution {

    static class Attack {
        int time;
        int damage;

        Attack(int time, int damage) {
            this.time = time;
            this.damage = damage;
        }
    }

    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int cnt = 0;
        int hp = health;

        Deque<Attack> queue = new ArrayDeque<>();
        for (int i = 0; i < attacks.length; i++) {
            queue.offer(new Attack(attacks[i][0], attacks[i][1]));
        }

        for (int time = 1; time <= 1001; time++) {
            if (queue.isEmpty()) {
                answer = hp;
                break;
            } else {
                if (queue.peek().time == time) {
                    Attack attack = queue.poll();
                    cnt = 0;
                    hp = hp - attack.damage;
                    if (hp <= 0) {
                        answer = -1;
                        break;
                    }

                } else {
                    hp = Math.min(health, hp + bandage[1]);
                    cnt++;
                    if (cnt == bandage[0]) {
                        hp = Math.min(health, hp + bandage[2]);
                        cnt = 0;
                    }
                }

            }
        }

        return answer;
    }
}