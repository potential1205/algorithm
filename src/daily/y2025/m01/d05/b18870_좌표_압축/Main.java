package daily.y2025.m01.d05.b18870_좌표_압축;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] copy = new int[N];

        Map<Integer, Integer> map = new HashMap<>();

        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            int val =  Integer.parseInt(st.nextToken());
            copy[i] = val;
            arr[i] = val;
        }

        int seq = 0;

        Arrays.sort(copy);

        for (int val : copy) {
            if (!map.containsKey(val)) {
                map.put(val, seq);
                seq++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int val : arr) {
            sb.append(map.get(val)).append(" ");
        }

        System.out.print(sb);
    }
}
