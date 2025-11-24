package study_archive.week10.b1700_멀티탭_스케줄링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,K,answer;
    static int[] multitap;
    static int[] inputs;

    static boolean isAlreadyUse(int num){
        for(int i=0; i<N; i++){
            if(multitap[i]==num) return true;
        }

        return false;
    }

    static int getAvailableIndex(){
        for(int i=0; i<N; i++){
            if(multitap[i]==0) return i;
        }

        return -1;
    }

    static int change(int start){
        int[] check = new int[N];
        Arrays.fill(check,200);

        for(int i=0; i<N; i++){
            int num = multitap[i];

            for(int j=start; j<K; j++){
                if(num==inputs[j]){
                    check[i] = j;
                    break;
                }
            }
        }

        int changeIndex = 0;
        int maxVal = check[0];

        for (int i=1; i<N; i++){
            if(maxVal < check[i]){
                maxVal = check[i];
                changeIndex = i;
            }
        }

        return changeIndex;
    }

    static void solution(){
        for(int i=0; i<K; i++){
            int num = inputs[i];

            if (isAlreadyUse(num)) continue;

            int index = getAvailableIndex();

            if (index!=-1) multitap[index] = num;
            else {
                int changeIndex = change(i);
                multitap[changeIndex] = num;
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        multitap = new int[N];
        inputs = new int[K];

        st = new StringTokenizer(bf.readLine());

        for (int i=0; i<K; i++){
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        solution();
    }
}