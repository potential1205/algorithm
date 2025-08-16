package streak.d20250816.b1141_접두사;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<String> li = new ArrayList<>();
        for(int i = 0; i < N; i++)
            li.add(br.readLine());

        Collections.sort(li, new Comparator<String>(){
            public int compare(String s1, String s2){
                return s1.length()-s2.length();
            }
        });

        int ans = N;
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                if(li.get(j).startsWith(li.get(i))){
                    ans--;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}