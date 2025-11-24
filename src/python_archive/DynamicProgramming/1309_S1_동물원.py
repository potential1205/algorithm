


if __name__ == "__main__":
    n = int(input())

    if n == 1:
        print(3)
    elif n == 2:
        print(7)
    else:
        dp = [1 for i in range(n+1)]

        dp[1], dp[2] = 3, 7
        for i in range(3,n+1):
            dp[i] = (2*dp[i-1] + dp[i-2]) % 9901 # 나머지 분배법칙

        print(dp[n])