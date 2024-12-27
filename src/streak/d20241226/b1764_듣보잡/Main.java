package streak.d20241226.b1764_듣보잡;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, size;
    static Map<String, Boolean> temp1;
    static PriorityQueue<String> pq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        temp1 = new HashMap<>();
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            temp1.put(bf.readLine(), true);
        }

        for (int i = 0; i < M; i++) {
            String value = bf.readLine();
            if (temp1.containsKey(value)) {
                pq.add(value);
                size++;
            }
        }

        System.out.println(size);

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.println(sb);
    }
}