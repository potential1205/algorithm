package daily.y2025.m03.d24.b2470_두_용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int p1 = 0;
        int p2 = n-1;

        int left = 0;
        int right = n-1;

        answer = Math.abs(arr[0] + arr[n-1]);

        while (true) {
            if (p1 == p2) {
                break;
            }

            if (answer > Math.abs(arr[p1] + arr[p2])) {
                answer = Math.abs(arr[p1] + arr[p2]);
                left = p1;
                right = p2;
            }

            if (arr[p1] + arr[p2] < 0) {
                p1++;
            } else if (arr[p1] + arr[p2] > 0) {
                p2--;
            } else {
                left = p1;
                right = p2;
                break;
            }
        }

        System.out.println(arr[left] + " " + arr[right]);
    }
}
