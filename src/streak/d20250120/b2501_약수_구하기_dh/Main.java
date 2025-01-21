package streak.d20250120.b2501_약수_구하기_dh;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int res = 0;
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i=1; i<=N; i++){
            if(N%i==0){
                arr.add(i);
            }

            if (arr.size()==K) {
                res = i;
                break;
            }

        }

        if (arr.size() < K){
            res = 0;
        }

        System.out.println(res);
    }
}
