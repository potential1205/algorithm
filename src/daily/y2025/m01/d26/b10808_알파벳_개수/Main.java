package daily.y2025.m01.d26.b10808_알파벳_개수;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < 26; i++) {
            map.put((char) (97 + i), 0);
        }

        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.get(str.charAt(i))+1);
        }

        for(int i = 0; i < 26; i++) {
            System.out.print(map.get((char) (97 + i)) + " ");
        }

    }
}