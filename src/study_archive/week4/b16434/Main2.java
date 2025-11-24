package study_archive.week4.b16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int N;
    static long[][] info;

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

    static boolean check(long maxHp){

        for(int i=0; i<N; i++){

        }
        return true;
    }

    static void binarySearch(){

        long start = 1;
        long end = Long.MAX_VALUE;

        while(start<=end){
            long mid = (start+end)/2; // mid : 최대 체력

            if(check(mid)){

            } else{

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        info = new long[N][3];

        User user = new User(0,0,0);
        user.attack = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            long type = Integer.parseInt(st.nextToken()); // 몬스터     or 포션
            long attack = Integer.parseInt(st.nextToken()); // 몬스터공격력 or 공격력 증가량
            long hp = Integer.parseInt(st.nextToken()); // 몬스터체력  or 체력 증가량
            info[i] = new long[]{type, attack, hp};
        }

        binarySearch();
        System.out.println(user.maxHp+1);
    }
}
