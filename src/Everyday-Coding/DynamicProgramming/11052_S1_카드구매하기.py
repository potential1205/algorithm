





if __name__ == "__main__":
    n = int(input())
    lst = [0] + list(map(int,input().split()))
    dp = [0]*(n+1)

    for i in range(1,n+1):
        for j in range(1,i+1):
            dp[i] = max(dp[i], dp[i-j] + lst[j])

    
    print(dp[-1])