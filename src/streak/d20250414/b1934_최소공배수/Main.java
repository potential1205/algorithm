package streak.d20250414.b1934_최소공배수;

import java.io.*;
import java.util.*;

public class Main {

    static int findGCD(int a, int b){
        while(b != 0){
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i <  T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(a * b / findGCD(a, b) +"\n");
        }

        System.out.println(sb);
    }
}
