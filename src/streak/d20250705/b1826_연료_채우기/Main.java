package streak.d20250705.b1826_연료_채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;

    static class Node {
        int x;
        int gas;

        Node (int x, int gas) {
            this.x = x;
            this.gas = gas;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        List<Node> gasStationList = new ArrayList<>();

        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int gas = Integer.parseInt(st.nextToken());

            gasStationList.add(new Node(x, gas));
        }

        Collections.sort(gasStationList, Comparator.comparingInt(node -> node.x));

        st = new StringTokenizer(bf.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> fuelQueue = new PriorityQueue<>(Collections.reverseOrder());

        int answer = 0;

        while (P < L) {
            while (!gasStationList.isEmpty() && gasStationList.get(0).x <= P) {
                fuelQueue.offer(gasStationList.remove(0).gas);
            }

            if (fuelQueue.isEmpty()) {
                System.out.println(-1);
                return;
            }

            answer++;
            P += fuelQueue.poll();
        }

        System.out.println(answer);
    }

}
