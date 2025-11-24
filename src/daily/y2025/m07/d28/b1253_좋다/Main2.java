package daily.y2025.m07.d28.b1253_좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성능 개선
public class Main2 {
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

		int count = 0;

		for (int i = 0; i < n; i++) {
			int target = arr[i];
			int left = 0;
			int right = n - 1;

			while (left < right) {
				if (left == i) {
					left++;
					continue;
				}

				if (right == i) {
					right--;
					continue;
				}

				int sum = arr[left] + arr[right];
				if (sum == target) {
					count++;
					break;
				}

				if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}

		System.out.println(count);
	}
}
