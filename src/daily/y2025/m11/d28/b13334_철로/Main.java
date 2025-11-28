package daily.y2025.m11.d28.b13334_철로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair>{
        int start;
        int end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());
            list.add(new Pair(val1, val2));
        }

        Collections.sort(list);
        st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());

        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Pair pair : list) {

            // 시작점이 벗어난 경우 제거
            while (!pq.isEmpty() && pq.peek() < pair.end - d) {
                pq.poll();
            }

            // 시작점이 범위안에 들어오면 큐에 포함
            if (pair.end - d <= pair.start) {
                pq.offer(pair.start);
            }

            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
    }
}
