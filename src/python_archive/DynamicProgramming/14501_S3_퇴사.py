


if __name__ == "__main__":
    n = int(input())
    infos = []
    for i in range(n):
        t,p = map(int,input().split())
        infos.append([t,p])
    
    dp = [0] * (n+1)

    for i in range(n-1,-1,-1):
        print(i+infos[i][0])
        if i+infos[i][0] <= n: # 상담가능
            dp[i] = max(dp[i+1],infos[i][1] + dp[i+infos[i][0]])
        else:
            dp[i] = dp[i+1]
    
    print(dp[0])




