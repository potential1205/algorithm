package daily.y2025.m08.d06.b9184_신나는_함수_실행;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][][] dp = new int[51][51][51];

	static int recursive(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}

		if (a > 20 || b > 20 || c > 20) {
			return dp[a][b][c] = recursive(20, 20, 20);
		}

		if (dp[a][b][c] != Integer.MAX_VALUE) {
			return dp[a][b][c];
		}

		if (a < b &&  b < c) {
			return dp[a][b][c] = recursive(a, b, c - 1) + recursive(a, b - 1, c - 1) - recursive(a, b - 1, c);
		}

		return dp[a][b][c] = recursive(a-1, b, c) + recursive(a-1, b-1, c) + recursive(a-1, b, c-1) - recursive(a-1, b-1, c-1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 51; i++) {
			for (int j = 0; j < 51; j++) {
				for (int k = 0; k < 51; k++) {
					dp[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1 && c == -1) {
				break;
			}

			int result = recursive(a, b, c);
			System.out.println("w(" + a + ", " + b + ", " + c + ")" + " = " + result);
		}
	}
}
