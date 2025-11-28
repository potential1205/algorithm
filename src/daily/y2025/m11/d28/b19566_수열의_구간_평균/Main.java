package daily.y2025.m11.d28.b19566_수열의_구간_평균;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*

    1. 문제 단순화
        구간의 평균을 구하려면 구간의 합과 길이를 알아야하는데 경우의 수가 많으므로
        구해야하는 평균으로 각 배열의 값을 빼준 후 그 합이 0인 경우를 찾는 것이 더 편함

        입력
        10 3
        2 4 3 1 5 3 2 4 1 3

        평균 뺀 후 누적합
        [-1,  1,  0, -2,  2,  0, -1,  1, -2,  0]

        이제 구간의 합이 0이 되는 구간의 개수를 구해야함
        아이디어는 같은 숫자 쌍을 찾는 것 왜냐하면
        prefix[r] - prefix[l-1] == 0 이어야하기 때문에 prefix[r] == prefix[l-1]을 찾는 것
        6번째 인덱스 -1을 기준으로 살펴보면
        0 ~ 0번째 구간의 합과, 0번쨰 ~ 6번째 구간의 합이 각각 -1이므로 이 둘의 구간 합은 0이 된다.
        따라서 answer에 1 추가됨

 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long prefix = 0;
        long answer = 0;

        HashMap<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);

        for (int i = 0; i < n; i++) {
            prefix += (arr[i] - k);
            long cnt = map.getOrDefault(prefix, 0L);
            answer += cnt;
            map.put(prefix, cnt + 1);
        }

        System.out.println(answer);
    }
}
