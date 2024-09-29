package samsung_B.week1.수열편집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,M,L;
    static List<Integer> data;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            data = new LinkedList<>();

            st = new StringTokenizer(bf.readLine());

            for(int i=0; i<N; i++){
                data.add(Integer.parseInt(st.nextToken()));
            }

            for(int i=0; i<M; i++){
                st = new StringTokenizer(bf.readLine());
                String cmd = st.nextToken();
                if(cmd.equals("I")){
                    int index = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    data.add(index,value);

                } else if(cmd.equals("D")){
                    int index = Integer.parseInt(st.nextToken());
                    data.remove(index);

                } else if(cmd.equals("C")){
                    int index = Integer.parseInt(st.nextToken());
                    int val = Integer.parseInt(st.nextToken());
                    data.set(index,val);
                }
            }

            if(data.size()-1>=L){
                System.out.println("#" + t + " " + data.get(L));
            } else{
                System.out.println("#" + t + " " + -1);
            }
        }
    }
}
