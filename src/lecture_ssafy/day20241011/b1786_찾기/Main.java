package lecture_ssafy.day20241011.b1786_찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String str1;
    static String str2;
    static int leng1, leng2;
    static List<Integer> indexList;


    static void solution(){

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        str1 = new String(bf.readLine());
        str2 = new String(bf.readLine());

        leng1 = str1.length();
        leng2 = str2.length();

        indexList = new ArrayList<>();

        solution();
    }
}
