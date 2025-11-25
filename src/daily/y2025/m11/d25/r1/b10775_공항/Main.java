package daily.y2025.m11.d25.r1.b10775_공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] pointers;

    static int find(int cur) {
        if (pointers[cur] == cur) {
            return cur;
        }

        int next = find(pointers[cur]);
        return pointers[cur] = next;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        pointers = new int[g + 1];

        for (int i = 0; i <= g; i++) {
            pointers[i] = i;
        }

        for (int i = 0; i < p; i++) {
            int gate = Integer.parseInt(br.readLine());
            int assigned = find(gate);
            if (assigned == 0) break;
            pointers[assigned] = find(assigned - 1);
            answer++;
        }

        System.out.println(answer);
    }
}
