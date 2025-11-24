import bisect

if __name__ == "__main__":
    n = int(input())
    lst = list(map(int,input().split()))

    dp = [lst[0]]
    
    for i in range(n):
        if dp[-1] < lst[i]:
            dp.append(lst[i])
        else:
            idx = bisect.bisect_left(dp,lst[i])
            dp[idx] = lst[i]

    print(len(dp))
    print(*dp)