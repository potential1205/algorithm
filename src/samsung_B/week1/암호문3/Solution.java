package samsung_B.week1.암호문3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int N,M;
    static List<String> data;

    static void insert(StringTokenizer st){
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        for(int i=0; i<y; i++){
            String newData = st.nextToken();
            data.add(x+i,newData);
        }
    }

    static void delete(StringTokenizer st){
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        for(int i=0; i<y; i++){
            data.remove(x);
        }
    }

    static void add(StringTokenizer st){
        int y = Integer.parseInt(st.nextToken());

        for(int i=0; i<y; i++){
            data.add(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t=1; t<=10; t++){
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());
            data = new LinkedList<>();

            for(int i=0; i<N; i++){
                data.add(st.nextToken());
            }

            st = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());

            for(int i=0; i<M; i++) {
                String cmd = st.nextToken();

                if (cmd.equals("I")) {
                    insert(st);
                } else if (cmd.equals("D")) {
                    delete(st);
                } else if (cmd.equals("A")) {
                    add(st);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ");
            for(int i=0; i<10; i++){
                sb.append(data.get(i)).append(" ");
            }
            System.out.println(sb);
        }
    }
}
