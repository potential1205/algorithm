if __name__ == "__main__":
    n,m,k = map(int,input().split())
    lst = list(map(int,input().split()))

    start, end = 1, lst[-1]-lst[0]
    result = []

    while start <= end:
        mid = (start+end)//2
        std, cnt = 0, 0
        points=[]
        
        for i in range(len(lst)):
            if std <= lst[i]:
                cnt+=1
                std = lst[i] + mid
                if cnt <= m:
                    points.append(i)
            else:
                continue
        
        if cnt < m:
            end = mid-1
        elif cnt >= m:
            start = mid+1
            result = points[:]


    for idx in range(k):
        if idx in result:
            print(1,end='')
        else:
            print(0,end='')