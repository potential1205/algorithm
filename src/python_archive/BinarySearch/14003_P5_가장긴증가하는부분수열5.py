import bisect

if __name__ == "__main__":
    n = int(input())
    lst = list(map(int,input().split()))

    dp = [lst[0]]

    result = [0] * n

    for i in range(n):
        if dp[-1] < lst[i]:
            dp.append(lst[i])
            result[i] = len(dp)-1
        else:
            idx = bisect.bisect_left(dp,lst[i])
            dp[idx] = lst[i]
            result[i] = idx

    print(len(dp))

    abc = []
    sdx = result.index(max(result))
    std = len(dp)-1

    while sdx>=0:
        if std == result[sdx]:
            abc.append(lst[sdx])
            sdx-=1
            std-=1
        else:
            sdx-=1
    abc.reverse()
    print(*abc)

