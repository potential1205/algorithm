package streak.d20250104.b1271_엄청난_부자_2;

import java.util.*;
import java.math.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        BigInteger n = sc.nextBigInteger();
        BigInteger m = sc.nextBigInteger();

        System.out.println(n.divide(m));
        System.out.println(n.remainder(m));


    }

}