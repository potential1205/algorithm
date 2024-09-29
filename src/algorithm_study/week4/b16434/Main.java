package algorithm_study.week4.b16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static class User{
        long maxHp;
        long hp;
        long attack;
        User(long maxHp, long hp, long attack){
            this.maxHp = maxHp;
            this.hp = hp;
            this.attack = attack;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        User user = new User(0,0,0);
        user.attack = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            long type = Integer.parseInt(st.nextToken()); // 몬스터     or 포션
            long attack = Integer.parseInt(st.nextToken()); // 몬스터공격력 or 공격력 증가량
            long hp = Integer.parseInt(st.nextToken()); // 몬스터체력  or 체력 증가량

            if(type==1){ // 몬스터
                user.hp += hp%user.attack==0 ? ((hp/(user.attack)-1) * attack) : (hp/user.attack) * attack;
                user.maxHp = Math.max(user.maxHp, user.hp);
            } else{ // 포션
                user.attack += attack;
                user.hp = Math.max(user.hp-hp,0);
            }
        }

        System.out.println(user.maxHp+1);
    }
}
