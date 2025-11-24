package daily.y2025.m06.d19.p42583_다리를_지나는_트럭;

import java.util.*;

class Solution {

    static class Truck {
        int weight;
        int finishTime;

        Truck (int weight, int finishTime) {
            this.weight = weight;
            this.finishTime = finishTime;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int n = truck_weights.length;
        int time = 1;
        int curWeight = 0;
        int finishCnt = 0;
        int startCnt = 0;

        Queue<Truck> queue = new LinkedList<>();

        while (true) {
            if (!queue.isEmpty() && queue.peek().finishTime == time) {
                Truck truck = queue.poll();
                curWeight -= truck.weight;
                finishCnt++;
            }

            if (finishCnt == n) {
                break;
            }

            if (startCnt < n && curWeight + truck_weights[startCnt] <= weight) {
                queue.offer(new Truck(truck_weights[startCnt], time + bridge_length));
                curWeight += truck_weights[startCnt];
                startCnt++;
            }

            time++;
        }

        return time;
    }
}