package streak.d20250728.b1253_좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static boolean isPossibleSmaller(int idx, int[] arr) {
		for (int j = 0; j < idx; j++) {
			for (int k = 0; k < idx; k++) {
				if (j != k && arr[j] + arr[k] == arr[idx]) {
					return true;
				}
			}
		}

		return false;
	}

	static boolean isPossibleBigger(int idx, int[] arr, int n) {
		for (int j = idx + 1; j < n; j++) {
			for (int k = idx + 1; k < n; k++) {
				if (j != k && arr[j] + arr[k] == arr[idx]) {
					return true;
				}
			}
		}

		return false;
	}

	static boolean isPossibleSmallerAndBigger(int idx, int[] arr, int n) {
		for (int left = 0; left < idx; left++) {
			for (int right = idx + 1; right < n; right++) {
				if (left != right && arr[left] + arr[right] == arr[idx]) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int answer = 0;

		for (int i = 0; i < n; i++) {
			if (isPossibleSmallerAndBigger(i, arr, n)) {
				answer++;
				continue;
			}

			if (isPossibleSmaller(i, arr)) {
				answer++;
				continue;
			}

			if (isPossibleBigger(i, arr, n)) {
				answer++;
			}
		}

		System.out.println(answer);
	}
}
