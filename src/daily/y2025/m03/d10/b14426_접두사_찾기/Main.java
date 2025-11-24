package daily.y2025.m03.d10.b14426_접두사_찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static Node root;

    static class Node {
        Map<Character, Node> child = new HashMap<>();
    }

    static void add(String str) {
        Node cur = root;

        for(char ch : str.toCharArray()) {
            cur.child.putIfAbsent(ch, new Node());
            cur = cur.child.get(ch);
        }
    }

    static boolean check(String str) {
        Node cur = root;

        for(char ch : str.toCharArray()) {
            if(!cur.child.containsKey(ch)) {
                return false;
            }

            cur = cur.child.get(ch);
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new Node();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            add(str);
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            String str = br.readLine();

            if (check(str)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
