
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static TreeMap<Integer,TreeSet<Problem>> R1 = new TreeMap<>();
    static TreeSet<Problem> R2 = new TreeSet<>();
    static Map<Integer, Problem> problemMap = new HashMap<>();

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 문제 번호
            int l = Integer.parseInt(st.nextToken()); // 난이도
            int g = Integer.parseInt(st.nextToken()); // 알고리즘 분류

            if (!R1.containsKey(g)) {
                TreeSet<Problem> tmp = new TreeSet<>();
                tmp.add(new Problem(p,l));
                R1.put(g,tmp);
            } else {
                R1.get(g).add(new Problem(p,l));
            }

            R2.add(new Problem(p,l,g));
            problemMap.put(p, new Problem(p,l,g));
        }

        M = Integer.parseInt(br.readLine());
        int p,l,g,x;
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "add":
                    p = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    g = Integer.parseInt(st.nextToken());
                    add(p,l,g);
                    break;
                case "recommend" :
                    g = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    recommend(g,x);
                    break;
                case "recommend2" :
                    x = Integer.parseInt(st.nextToken());
                    recommend2(x);
                    break;
                case "recommend3" :
                    x = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    recommend3(x, l);
                    break;
                case "solved":
                    p = Integer.parseInt(st.nextToken());
                    solved(p);
                    break;
            }

        }
        System.out.println(sb.toString());
    }

    public static int recommend(int G, int x) {
        switch (x) {
            case 1:
                sb.append(R1.get(G).last().P).append("\n");
                break;
            case -1:
                sb.append(R1.get(G).first().P).append("\n");
                break;
        }
        return 0;
    }
    public static void recommend2(int x) {
        switch (x) {
            case 1:
                sb.append(R2.last().P).append("\n");
                break;
            case -1:
                sb.append(R2.first().P).append("\n");
                break;
        }
    }
    public static void recommend3(int x, int L) {
        if (x==1) {
            Problem tmp = R2.lower(new Problem(L));
            if (tmp!=null) {
                sb.append(tmp.P);
            } else {
                sb.append(-1);
            }

        } else if(x==-1) {
            Problem tmp = R2.higher(new Problem(L));
            if (tmp!=null) {
                sb.append(tmp.P);
            } else {
                sb.append(-1);
            }
        }
    }

    public static void add(int P, int L, int G) {
        R1.get(G).add(new Problem(P,L));
        R2.add(new Problem(P,L,G));
        problemMap.put(P, new Problem(P,L,G));
    }

    public static void solved(int P) {
        Problem findProblem = problemMap.get(P);
        R1.get(findProblem.G).remove(new Problem(P, true));
        R2.remove(new Problem(P, true));
        problemMap.remove(P);
    }
}

class Problem implements Comparable<Problem>{
    int P, L, G;

    Problem(int P, int L, int G){
        this.P = P;
        this.L = L;
        this.G = G;
    }

    Problem(int P, int L){
        this.P = P;
        this.L = L;
    }

    Problem(int L){
        this.L = L;
    }

    Problem(int P, boolean Ptrue){
        this.P = P;
    }

    public int compareTo(Problem o){
        if(this.L > o.L){
            return 1;
        }else if(this.L < o.L){
            return -1;
        }else{
            if(this.P > o.P){
                return 1;
            }else if(this.P < o.P){
                return -1;
            }else{
                return 0;
            }
        }
    }


}