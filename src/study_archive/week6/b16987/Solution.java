package study_archive.week6.b16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N,answer;
    static Egg[] eggs;

    static class Egg{
        int hp, attack;
        boolean broken;

        Egg(int hp, int attack, boolean broken){
            this.hp = hp;
            this.attack = attack;
            this.broken = broken;
        }
    }

    static boolean isAllBroken(int me){
        int cnt = 0;
        for(int i=0; i<N; i++){
            Egg egg = eggs[i];
            if(i!=me && egg.broken) cnt++;
        }

        return cnt==N-1 ? true : false;
    }

    static void solution(int depth){
        if(depth==N){
            int cum = 0;
            for(int i=0; i<N; i++) {
                Egg egg = eggs[i];
                if (egg.broken) cum++;
            }
            answer = Math.max(answer,cum);
            return;
        }

        Egg egg1 = eggs[depth];
        if(egg1.broken || isAllBroken(depth)) {
            solution(depth+1);
            return;
        }

        for(int i=0; i<N; i++){
            Egg egg2 = eggs[i];
            if(egg2.broken || i==depth) continue;

            Egg originalEgg1 = new Egg(egg1.hp, egg1.attack, egg1.broken);
            Egg originalEgg2 = new Egg(egg2.hp, egg2.attack, egg2.broken);

            egg1.hp -= egg2.attack;
            egg2.hp -= egg1.attack;

            if(egg1.hp<=0) egg1.broken = true;
            if(egg2.hp<=0) egg2.broken = true;

            solution(depth+1);

            egg1.hp = originalEgg1.hp;
            egg1.broken = originalEgg1.broken;

            egg2.hp = originalEgg2.hp;
            egg2.broken = originalEgg2.broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        eggs = new Egg[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            int hp = Integer.parseInt(st.nextToken());
            int attack = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(hp, attack, false);
        }

        answer = 0;
        solution(0);
        System.out.println(answer);
    }
}
