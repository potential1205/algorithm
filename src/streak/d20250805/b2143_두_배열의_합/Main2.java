package streak.d20250805.b2143_두_배열의_합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {

	static long lowerBound(List<Long> list, long key) {
		int left = 0;
		int right = list.size() - 1;
		long result = list.size();

		while (left <= right) {
			int mid = (left + right) / 2;

			if (list.get(mid) >= key)  {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return result;
	}

	static long upperBound(List<Long> list, long key) {
		int left = 0;
		int right = list.size() - 1;
		long result = list.size();

		while (left <= right) {
			int mid = (left + right) / 2;

			if (list.get(mid) > key) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		int[] arr1 = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());

		int[] arr2 = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}

		long[] ps1 = new long[n + 1];
		ps1[0] = 0;
		for (int i = 1; i <= n; i++) {
			ps1[i] = ps1[i - 1] + arr1[i - 1];
		}

		long[] ps2 = new long[m + 1];
		ps2[0] = 0;
		for (int i = 1; i <= m; i++) {
			ps2[i] = ps2[i - 1] + arr2[i - 1];
		}

		List<Long> list1 = new ArrayList<>();
		List<Long> list2 = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				long sum = ps1[j] - ps1[i];
				list1.add(sum);
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j <= m; j++) {
				long sum = ps2[j] - ps2[i];
				list2.add(sum);
			}
		}

		Collections.sort(list1);
		Collections.sort(list2);

		long answer = 0;
		for (long v1 : list1) {
			long v2 = t - v1;
			long lo = lowerBound(list2, v2);
			long hi = upperBound(list2, v2);
			answer += (hi - lo);
		}

		System.out.println(answer);
	}
}
