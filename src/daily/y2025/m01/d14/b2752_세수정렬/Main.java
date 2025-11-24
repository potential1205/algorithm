package daily.y2025.m01.d14.b2752_세수정렬;

import java.util.*;

public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();

        list.add(sc.nextInt());
        list.add(sc.nextInt());
        list.add(sc.nextInt());

        Collections.sort(list);

        for (int i=0; i<3; i++){
            System.out.print(list.get(i) + " ");

        }
    }


}