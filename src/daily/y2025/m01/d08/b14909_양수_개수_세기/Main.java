package daily.y2025.m01.d08.b14909_양수_개수_세기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int answer = 0;

        while (st.hasMoreTokens()) {
            if (Integer.parseInt(st.nextToken()) > 0) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
