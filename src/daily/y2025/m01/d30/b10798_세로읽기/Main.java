package daily.y2025.m01.d30.b10798_세로읽기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Character[][] arr = new Character[5][15];

        for(int i = 0; i < 5; i++) {
            String str = bf.readLine();

            for(int j = 0; j < str.length(); j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[j][i] != null) {
                    sb.append(arr[j][i]);
                }
            }
        }

        System.out.println(sb);
    }
}
