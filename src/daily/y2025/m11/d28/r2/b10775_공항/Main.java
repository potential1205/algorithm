package daily.y2025.m11.d28.r2.b10775_공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] pointers;

    static int find(int num) {
        if (pointers[num] == num) {
            return num;
        }

        int next = find(pointers[num]);
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
            int num = Integer.parseInt(br.readLine());
            int assinged = find(num);
            if (assinged == 0) break;
            answer++;
            pointers[assinged] = find(assinged - 1);
        }

        System.out.println(answer);
    }
}
