package daily.y2024.m12.d30.b21944_문제_추천_시스템_version_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static int N,M;
    static StringBuilder sb = new StringBuilder();

    static class Problem implements Comparable<Problem>{
        int num;
        int level;
        int type;

        Problem(int num, int level){
            this.num = num;
            this.level = level;
        }

        Problem(int level, int type, boolean temp){
            this.level = level;
            this.type = type;
        }

        public int compareTo(Problem o2){
            if(level > o2.level){
                return 1;
            }else if(level < o2.level){
                return -1;
            }else{
                if(num > o2.num){
                    return 1;
                }else if(num < o2.num){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        ArrayList<TreeSet<Problem>> R1 = new ArrayList<>();
        for(int i=0; i<=100; i++){
            R1.add(new TreeSet<>());
        }

        TreeSet<Problem> R2 = new TreeSet<>();
        HashMap<Integer, Problem> map = new HashMap<>();

        N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());

            R1.get(type).add(new Problem(num, level));
            R2.add(new Problem(num, level));
            map.put(num, new Problem(level, type, true));
        }

        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<M; i++){
            st = new StringTokenizer(bf.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("recommend")){
                int type = Integer.parseInt(st.nextToken());
                int input = Integer.parseInt(st.nextToken());

                if(input == 1){
                    sb.append(R1.get(type).last().num + "\n");
                }else if(input == -1){
                    sb.append(R1.get(type).first().num + "\n");
                }
            }

            if(cmd.equals("recommend2")){
                int input = Integer.parseInt(st.nextToken());

                if(input == 1){
                    sb.append(R2.last().num + "\n"); // swap..?
                }
                if(input == -1){
                    sb.append(R2.first().num + "\n"); // swap?
                }
            }

            if(cmd.equals("recommend3")){
                int input = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());

                if(input == 1){
                    Problem temp = R2.higher(new Problem(0, level));
                    if(temp == null){
                        sb.append(-1 + "\n");
                    }else{
                        sb.append(temp.num + "\n");
                    }
                }

                if(input == -1){
                    Problem temp = R2.lower(new Problem(0, level));
                    if(temp == null){
                        sb.append(-1 + "\n");
                    }else{
                        sb.append(temp.num + "\n");
                    }
                }
            }

            if(cmd.equals("add")){
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                int type = Integer.parseInt(st.nextToken());

                R1.get(type).add(new Problem(num, level));
                R2.add(new Problem(num, level));
                map.put(num, new Problem(level, type, true));
            }

            if(cmd.equals("solved")){
                int num = Integer.parseInt(st.nextToken());
                int level = map.get(num).level;
                int type = map.get(num).type;

                R1.get(type).remove(new Problem(num, level));
                R2.remove(new Problem(num, level));
                map.remove(num);
            }
        }

        System.out.println(sb);
    }
}
