package streak.d20250122.b9946_단어_퍼즐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Main {

    static boolean solve(String input, String output) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char ch =  input.charAt(i);

            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch)+1);
            } else {
                map.put(ch, 1);
            }
        }

        for (int i = 0; i < output.length(); i++) {
            char ch =  output.charAt(i);

            if (map.containsKey(ch)) {
                if (map.get(ch)-1 < 0) {
                    return false;
                } else {
                    map.put(ch, map.get(ch)-1);
                }
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;

        while (true) {
            String input = bf.readLine();
            String output = bf.readLine();

            if (input.equals("END")) break;

            if (solve(input, output)) {
                System.out.println("Case " + tc + ": same");
            } else {
                System.out.println("Case " + tc + ": different");
            }

            tc++;
        }
    }
}
