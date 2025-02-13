package streak.d20250213.b1826_연료_채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int position,fuel,index;

        Node(int position, int fuel) {
            this.position = position;
            this.fuel = fuel;
        }

        Node(int position, int fuel, int index) {
            this.position = position;
            this.fuel = fuel;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            int gap1 = o.fuel - this.fuel;

            if (gap1 == 0) {
                return this.position - o.position;
            }

            return gap1;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "position=" + position +
                    ", fuel=" + fuel +
                    ", index=" + index +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        List<Node> list = new ArrayList<>();

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int position = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            list.add(new Node(position, fuel));
        }

        Collections.sort(list, Comparator.comparingInt(o -> o.position));

        st = new StringTokenizer(bf.readLine());
        int target = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        int position = 0;
        int answer = 0;
        int idx = 0;

        while (true) {

            if (position + fuel >= target) {
                break;
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();

            for (int i = idx; i < n; i++) {
                Node node = list.get(i);

               if (node.position <= position + fuel) {
                   pq.offer(new Node(node.position, node.fuel, i));
               }
            }

            if (pq.isEmpty()) {
                answer = -1;
                break;
            }

            Node node = pq.poll();
            idx = node.index+1;

            fuel = fuel + node.fuel - (node.position - position);
            position = node.position;
            answer++;
        }

        System.out.println(answer);
    }
}
