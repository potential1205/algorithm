package streak.d20250911.p258707_N_카드게임;

import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;
        int round = 0;
        int idx = n / 3;

        Set<Integer> hand = new HashSet<>();
        Set<Integer> newCardSet = new HashSet();

        // 초기 카드 받기
        for (int i = 0; i < n / 3; i++) {
            hand.add(cards[i]);
        }

        while (true) {
            round++;

            if (idx >= n) break;

            // 카드 2장 뽑기
            newCardSet.add(cards[idx]);
            newCardSet.add(cards[idx + 1]);
            idx += 2;

            // 1. 손에 있는 카드끼리 쌍 만들기
            boolean found = false;
            for (int card : hand) {
                if (hand.contains(target - card)) {
                    hand.remove(card);
                    hand.remove(target - card);
                    found = true;
                    break;
                }
            }

            // 2. 손 카드 + 후보 카드로 쌍 만들기 (코인 1개)
            if (!found && coin >= 1) {
                for (int card : hand) {
                    if (newCardSet.contains(target - card)) {
                        hand.remove(card);
                        newCardSet.remove(target - card);
                        coin--;
                        found = true;
                        break;
                    }
                }
            }

            // 3. 후보 카드끼리 쌍 만들기 (코인 2개)
            if (!found && coin >= 2) {
                for (int card : newCardSet) {
                    if (newCardSet.contains(target - card)) {
                        newCardSet.remove(card);
                        newCardSet.remove(target - card);
                        coin -= 2;
                        found = true;
                        break;
                    }
                }
            }

            // 쌍을 만들 수 없으면 게임 종료
            if (!found) {
                break;
            }
        }

        return round;
    }

}