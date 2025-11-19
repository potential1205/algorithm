package streak.d20251119.b16934_게임_닉네임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
public class Main {

    static class Node {
        char ch;
        Node[] child = new Node[26];
    }

    // 트라이에서 나와 겹치는 접두사 길이 찾기
    static int find(Node root, String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';

            if (cur.child[idx] == null) {
                return i;
            } else {
                cur = cur.child[idx];
            }
        }

        return -1;
    }

    // 트라이에 단어 추가
    static void make(Node root, String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';

            if (cur.child[idx] == null) {
                cur.child[idx] = new Node();
                cur.child[idx].ch = str.charAt(i);
                cur = cur.child[idx];
            }  else {
                cur = cur.child[idx];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node();
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);

            int cnt = find(root, str);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= cnt; j++) {
                char ch = str.charAt(j);
                sb.append(ch);
            }


            if (cnt == -1) {
                int duplicateCnt = map.get(str);
                answer.append(duplicateCnt == 1 ? str : str + duplicateCnt).append("\n");
            } else {
                answer.append(sb).append("\n");
            }

            make(root, str);
        }

        System.out.println(answer);
    }
}
