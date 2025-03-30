package streak.d20250330.b1331_부재중_전화;

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] input = new char[n][];
        for (int i = 0; i < n; i++) {
            input[i] = br.readLine().toCharArray();
        }

        int len = input[0].length;
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char base = input[0][i];
            boolean isSame = true;

            for (int j = 1; j < n; j++) {
                if (input[j][i] != base) {
                    isSame = false;
                    break;
                }
            }

            sb.append(isSame ? base : '?');
        }

        System.out.print(sb);
    }
}
