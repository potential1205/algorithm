package daily.y2025.m07.d26.p42839_소수_찾기;

import java.util.*;

class Solution {
	static int n;
	static Set<Integer> set = new HashSet<>();
	static boolean[] visit;

	public static boolean isPrime2(int n) {
		if (n <= 1) return false;
		if (n == 2) return true;
		if (n % 2 == 0) return false;

		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) return false;
		}
		return true;
	}

	static boolean isPrime(int val) {
		int cnt = 0;
		for (int i = 1; i <= val; i++) {
			if (val % i == 0) {
				cnt ++;
			}

			if (cnt > 2) {
				return false;
			}
		}

		if (cnt == 2) {
			return true;
		} else {
			return false;
		}
	}

	static void dfs(int depth, String numbers, String cur) {
		if (depth == n) {
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				String newStr = cur + numbers.charAt(i);
				set.add(Integer.parseInt(newStr));
				visit[i] = true;
				dfs(depth + 1, numbers, newStr);
				visit[i] = false;
			}
		}
	}

	public static Set<Integer> getPrimesUpTo(int n) {
		boolean[] isComposite = new boolean[n + 1];
		Set<Integer> primes = new HashSet<>();

		for (int i = 2; i <= n; i++) {
			if (!isComposite[i]) {
				primes.add(i);
				for (int j = i * 2; j <= n; j += i) {
					isComposite[j] = true;
				}
			}
		}

		return primes;
	}


	public int solution(String numbers) {
		int answer = 0;
		n = numbers.length();
		visit = new boolean[n];

		dfs(0, numbers, "");

		for (int val : set) {
			if (isPrime2(val)) {
				answer++;
			}
		}

		return answer;
	}
}