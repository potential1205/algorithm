package algorithm_study.week11.b2632_피자판매;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int target,answer;
    static int N,M;
    static int[] arr1, arr2;
    static List<Integer> info1, info2;

    static void solution(){
        int cum = 0;

        for(int i=0; i<N; i++){ // 시작점
            cum = 0;

            for(int j=0; j<N; j++){ // 길이
                cum += arr1[(i+j)%N];
                info1.add(cum);
            }
        }

        for(int i=0; i<M; i++){ // 시작점
            cum = 0;

            for(int j=0; j<M; j++){ // 길이
                cum += arr2[(i+j)%M];
                info2.add(cum);
            }
        }

        info1.add(0);
        info2.add(0);

        Collections.sort(info1);
        Collections.sort(info2);

        int startIndex = 0;
        int endIndex = info2.size();

        for(int i=0; i<info1.size(); i++){
            for(int j=0; j<endIndex; j++){
                int val = info1.get(i) + info2.get(j);

                if(val==target){
                    answer++;
                } else if(val<target){
                    continue;
                } else if(val>target){
                    endIndex = j;
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        info1 = new ArrayList<>();
        info2 = new ArrayList<>();

        arr1 = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        arr2 = new int[M];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(bf.readLine());
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        solution();
    }
}
