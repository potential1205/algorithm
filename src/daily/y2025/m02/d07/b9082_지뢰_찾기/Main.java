package daily.y2025.m02.d07.b9082_지뢰_찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N, answer;
    static int[] nums, cnts;
    static char[] bombs;

    static boolean check(int index) {
        for (int i = -1; i < 2; i++) {
            if (index + i < 0 || index + i >= N) {
                continue;
            }

            if (cnts[index + i] + 1 > nums[index + i]) {
                return false;
            }
        }

        for (int i = -1; i < 2; i++) {
            if (index + i < 0 || index + i >= N) {
                continue;
            }

            cnts[index + i] += 1;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            String str = bf.readLine();

            nums = new int[N];
            cnts = new int[N];
            bombs = new char[N];
            answer = 0;

            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(str.charAt(i) + "");
            }

            str = bf.readLine();

            for (int i = 0; i < N; i++) {
                bombs[i] = str.charAt(i);
            }

            for (int i = 0; i < N; i++) {
                if (bombs[i] == '*' && check(i)) {
                    answer++;
                }
            }

            for (int i = 0; i < N; i++) {
                if (bombs[i] != '*' && check(i)) {
                    answer++;
                }
            }

            System.out.println(answer);
        }
    }
}
