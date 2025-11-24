package daily.y2025.m11.d24.b10775_공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] pointers;

    static int find(int cur) {
        if (cur == 0) return 0;

        if (pointers[cur] == cur) {
            return cur;
        }

        int next = find(pointers[cur]);
        pointers[cur] = next;
        return next;
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
            int requestNum = Integer.parseInt(br.readLine());
            int next = find(requestNum);
            if (next == 0) break;
            answer++;
            pointers[next] = find(next - 1);
        }

        System.out.println(answer);
    }
}
