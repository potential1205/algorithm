
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    dp = [0] * (n+1)

    for day in range(1,n+1):
        t,p = map(int,input().split())
        dp[day] = max(dp[day], dp[day-1])
        if day + t -1 <= n:
            dp[day+t-1] = max(dp[day+t-1], dp[day-1]+p)
    
    print(dp[-1])
