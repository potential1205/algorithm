package streak.d20241222.b15990;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(st.nextToken());
                arr[i][j] = value;
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer += arr[i][0];
            answer += arr[i][m-1];
            for (int j = 1; j < m; j++) {
                answer += Math.abs(arr[i][j-1]-arr[i][j]);
            }
        }

        for (int i = 0; i < m; i++) {
            answer += arr[0][i];
            answer += arr[n-1][i];

            for (int j = 1; j < n; j++) {
                answer += Math.abs(arr[j][i]-arr[j-1][i]);
            }
        }

        answer += (n*m*2);

        System.out.println(answer);
    }
}
