package streak.d20241220.b11723_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        int data = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());

            String ope = st.nextToken();

            if(ope.equals("all")) data = (1 << 21) - 1;
            else if(ope.equals("empty")) data = 0;
            else {
                int num = Integer.parseInt(st.nextToken());
                if(ope.equals("add"))
                    data |= (1 << num);
                else if(ope.equals("remove"))
                    data &= ~(1 << num);
                else if(ope.equals("check"))
                    sb.append((data & (1 << num)) != 0 ? 1 : 0).append("\n");
                else if(ope.equals("toggle"))
                    data ^= (1 << num);
            }
        }

        System.out.println(sb);
    }
}
