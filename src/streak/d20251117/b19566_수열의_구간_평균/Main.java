package streak.d20251117.b19566_수열의_구간_평균;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
    난이도 7
    수학적인 센스가 필요한 문제
    처음엔 세그먼트 트리로 접근하려고 했으나, 세그먼트 트리는 주어진 하나의 구간에 대한 합을 빠르게 찾아주는 것에 특화되어있고
    주어진 문제처럼 평균을 구해야하는 경우 다양한 구간의 길이를 고려해주기 어렵다고 판단함

    구간합과 누적합을 명확히 구분하자

    평균이 K인 구간을 구해야하는데 구간의 길이에 따라 구간의 합과 길이를 나누어줘야하기 때문에 경우의 수가 엄청나게 많다는 문제가 발생함
    그러면 모든 원소들에 대해 K만큼 빼주고 이 때 이 값들의 합이 0이 되는 구간을 찾아주면 된다고 생각함, 수학적으로 동일하니깐

    ex) 평균이 3인 경우 [2 4 3 1 5 3 2 4 1 3]
        평균만큼 빼준 후 [-1 1 0 -2 2 0 -1 1 -2 0]
        누적합 cums: [-1 0 0 -2 0 0 -1 0 -2 -2]

        이제 누적합 배열의 i = 0 부터 i = n 까지 탐색하면서 해당 값이 몇 번 나왔는지 freq 배열에 저장함
        i = 0 인 경우 freq[-1] = 1
        i = 1 인 경우 freq[0] = 2 (공집합 때문에 초기에 freq[0]은 이미 1임)
        i = 2 인 경우 freq[0] = 3
        ...

        i = 2 인 경우를 살펴보면 누적합 배열의 값이 0이고 freq[0]를 참조하면 지금까지 0 이었던 경우가 2번 있다는 걸 알 수 있고 현재 경우와 더해주므로
        answer는 +2가 됨
        즉, 현재 인덱스 idx 기준으로 cums[idx]라는 값이 이전까지 freq[cums[idx]] 만큼 등장했기 때문에
        현재 idx번째 값과 freq[cums[idx]]번 만큼 페어가 발생하므로 그만큼 answer에 더해주면 됨
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long prefix = 0;
        long answer = 0;

        // prefix 값 등장 횟수 저장
        Map<Long, Long> freq = new HashMap<>();

        // prefix = 0인 경우 1로 초기화 (공집합인 상태도 경우의 수에 포함)
        freq.put(0L, 1L);

        for (int i = 0; i < n; i++) {
            prefix += (arr[i] - k); // 모든 원소에 K 를 뺀 값에 대해 누적합을 구함, 그렇게 구한 누적합의 구간합이 0이 되는 경우를 찾으면 되므로
            long cnt = freq.getOrDefault(prefix, 0L);
            answer += cnt; // 이전에 같은 prefix가 cnt번이 등장했다면 현재 등장한 값과 조합할 수 있으므로 cnt만큼 새로운 구간이 생김
            freq.put(prefix, cnt + 1);
        }

        System.out.println(answer);
    }
}
