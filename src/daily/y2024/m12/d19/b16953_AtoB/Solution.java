package daily.y2024.m12.d19.b16953_AtoB;

import java.io.*;
import java.util.*;

// 고쳐야할 점
// 처음 DP로 착각함. 최소횟수!에 집중하여 BFS로 할 것. 또한 10^9이라 DP 배열 만들면 터질 듯.
// 그냥 Q로 시도했다가 최소 count 단위가 안나옴 -> PriorityQueue로 수정
// +1이 아니라 1을 더한 문자를 만들어라...!!!
public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long start = Integer.parseInt(str[0]);
        long end = Integer.parseInt(str[1]);
        PriorityQueue <long[]> q = new PriorityQueue<>((o1, o2) ->
        {
            return Long.compare(o1[1], o2[1]);
        }
        );
        q.offer(new long[] {start,1});
        long res = -1;

        while(!q.isEmpty()) {
            long[] now = q.poll();
            long num = now[0];
            long cnt = now[1];

            if(num>end) {
                continue;
            }

            if (num==end) {
                res = cnt;
                break;
            } else {
                q.offer(new long[] {num*2,cnt+1});
                q.offer(new long[] {Long.parseLong(num +"1"),cnt+1});
            }
        }

        System.out.println(res);
    }
}
