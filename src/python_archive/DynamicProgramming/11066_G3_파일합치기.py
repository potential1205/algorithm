import sys
input = sys.stdin.readline

if __name__ == "__main__":
    T = int(input())
    for t in range(T):
        k = int(input())
        lst = [0] + list(map(int,input().split()))

        s = [0 for _ in range(k+1)]
        for i in range(1,k+1):
            s[i] = s[i-1] + lst[i]
        
        dp = [[0 for j in range(k+1)] for i in range(k+1)]

        for i in range(2,k+1):
            for j in range(1,k+2-i):
                dp[j][j+i-1] = min([dp[j][j+q] + dp[j+q+1][j+i-1] for q in range(i-1)]) +(s[j+i-1] - s[j-1])

        print(dp[1][k])