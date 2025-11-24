package daily.y2025.m01.d06.b1940_주몽;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sp = 0;
        int ep = n-1;
        int answer = 0;

        while (sp < ep) {
            if (arr[sp] + arr[ep] == m) {
                answer++;
                sp++;
            } else if (arr[sp] + arr[ep] > m) {
                ep--;
            } else {
                sp++;
            }
        }

        System.out.println(answer);
    }
}
