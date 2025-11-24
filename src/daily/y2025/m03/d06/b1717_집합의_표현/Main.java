package daily.y2025.m03.d06.b1717_집합의_표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] p;

    static int find(int a) {
        if (p[a] < 0) {
            return a;
        }

        return p[a] = find(p[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        if (p[rootA] > p[rootB]) { // B가 더 큰 집합이면
            p[rootB] += p[rootA];
            p[rootA] = rootB;
        } else {
            p[rootA] += p[rootB];
            p[rootB] = rootA;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        p = new int[n+1];
        Arrays.fill(p, -1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int ope = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (ope == 0) {
                union(a,b);
            } else {
                if (find(a) == find(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}
