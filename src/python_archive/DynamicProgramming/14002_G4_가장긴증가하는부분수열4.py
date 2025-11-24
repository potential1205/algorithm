
if __name__ == "__main__":
    n = int(input())
    lst = list(map(int,input().split()))

    dp = [1 for i in range(n)]

    for i in range(n):
        for j in range(i):
            if lst[i] > lst[j]:
                dp[i] = max(dp[i],dp[j]+1)

    leng = max(dp)
    print(leng)

    idx = dp.index(max(dp))
    result = []

    while idx>=0:
        if leng == dp[idx]:
            result.append(lst[idx])
            leng-=1
        idx-=1

    result.reverse()
    print(*result)
