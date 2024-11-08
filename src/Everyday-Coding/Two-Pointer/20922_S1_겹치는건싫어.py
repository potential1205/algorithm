

if __name__ == "__main__":
    n,k = map(int,input().split())
    lst = list(map(int,input().split()))
    info = dict()
    set_lst = set(lst)
    for val in set_lst:
        info[val] = 0

    start, end = 0, 0
    max_cnt, cnt = 0, 0

    while start<=end:
        if end == n: break
        
        if max_cnt < cnt:
            max_cnt = cnt

        val = lst[end]

        if info[val] == k:
            target = lst[start]
            info[target] -= 1
            start+=1
            cnt-=1
        else:
            info[val]+=1
            end+=1
            cnt+=1

    print(max_cnt)
