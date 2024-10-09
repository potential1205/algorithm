package algorithm_study.week11.b2696_중앙값_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T,N;
    static List<Integer> list;
    static PriorityQueue<Integer> queue1;
    static PriorityQueue<Integer> queue2;

    static void solution(){
        List<Integer> answer = new ArrayList<>();

        queue1.offer(list.get(0));
        answer.add(list.get(0));

        for(int i=1; i<N; i++){
            if(i%2==0){
                queue1.offer(list.get(i));
            } else{
                queue2.offer(list.get(i));
            }

            if(queue1.size()>0 && queue2.size()>0 && queue1.peek()>queue2.peek()){
                int val1 = queue1.poll();
                int val2 = queue2.poll();
                queue1.offer(val2);
                queue2.offer(val1);
            }

            if(i%2==0){
                answer.add(queue1.peek());
            }
        }

        System.out.println(answer.size());
        for(int val : answer){
            System.out.print(val + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            queue1 = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
            queue2 = new PriorityQueue<>(); // 최소 힙
            list = new ArrayList<>();

            int lines = (N % 10 == 0) ? N / 10 : N / 10 + 1;

            for (int i = 0; i < lines; i++) {
                st = new StringTokenizer(bf.readLine());

                if(i==lines-1){
                    for(int j=0; j<N%10; j++){
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                } else{
                    for(int j=0; j<10; j++){
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                }
            }

            solution();
        }
    }
}
