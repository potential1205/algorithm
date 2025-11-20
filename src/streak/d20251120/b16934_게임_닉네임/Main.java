package streak.d20251120.b16934_게임_닉네임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static class Node {
        char ch;
        Node[] child = new Node[26];
    }

    static int findPrefix(Node root, String str) {
        int cnt = 0;
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';

            if (cur.child[idx] == null) {
                return cnt;
            } else {
                cur = cur.child[idx];
                cnt++;
            }
        }

        return -1;
    }

    static void addNickname(Node root, String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';

            if (cur.child[idx] == null) {
                cur.child[idx] = new Node();
                cur.child[idx].ch = str.charAt(i);
                cur = cur.child[idx];
            } else {
                cur = cur.child[idx];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 닉네임 저장
        HashMap<String, Integer> map = new HashMap<>();

        // 트라이 루트
        Node root = new Node();

        // 정답 출력
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String nickname = br.readLine();
            map.put(nickname, map.getOrDefault(nickname, 0) + 1); // 닉네임 저장
            int cnt = findPrefix(root, nickname); // 일치하는 접두사 길이 반환

            if (cnt == -1) { // str과 완전히 일치하는 접두사가 이미 존재한다면
                answer.append(nickname);
                if (map.get(nickname) > 1) answer.append(map.get(nickname));
                answer.append("\n");
            } else {
                StringBuilder prefix = new StringBuilder();
                for (int j = 0; j <= cnt; j++) {
                    prefix.append(nickname.charAt(j));
                }

                addNickname(root, nickname);
                answer.append(prefix).append("\n");
            }
        }

        System.out.println(answer);
    }
}
