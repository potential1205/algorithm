package daily.y2025.m08.d05.b2143_두_배열의_합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

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

		List<Long> set1 = new ArrayList<>();
		List<Long> set2 = new ArrayList<>();

		for (int size = 1; size <= n; size++) {
			long val = 0;

			for (int i = 0; i < size; i++) {
				val += arr1[i];
			}
			set1.add(val);

			for (int start = 1; start < n - size + 1; start++) {
				val -= (arr1[start - 1]);
				val += (arr1[start + size - 1]);
				set1.add(val);
			}
		}

		for (int size = 1; size <= m; size++) {
			long val = 0;

			for (int i = 0; i < size; i++) {
				val += arr2[i];
			}
			set2.add(val);

			for (int start = 1; start < m - size + 1; start++) {
				val -= (arr2[start - 1]);
				val += (arr2[start + size - 1]);
				set2.add(val);
			}
		}

		long answer = 0;

		Collections.sort(set1);
		Collections.sort(set2, Comparator.reverseOrder());
		int p1 = 0;
		int p2 = 0;

		while (p1 < set1.size() && p2 < set2.size()) {
			long a = set1.get(p1);
			long b = set2.get(p2);
			long val = a + b;

			if (val == t) {
				long cnt1 = 0;
				long cnt2 = 0;

				while (p1 < set1.size() && set1.get(p1) == a) {
					cnt1++;
					p1++;
				}
				while (p2 < set2.size() && set2.get(p2) == b) {
					cnt2++;
					p2++;
				}

				answer += cnt1 * cnt2;

			} else if (val > t) {
				p2++;
			} else if (val < t) {
				p1++;
			}
		}

		System.out.println(answer);
	}
}
