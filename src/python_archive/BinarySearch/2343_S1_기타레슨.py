
if __name__ == "__main__":
    n,m = map(int,input().split())
    lectures = list(map(int,input().split()))

    start, end = max(lectures)-1, sum(lectures)

    while start+1 < end:
        mid = (start+end)//2
        cum_time, cnt = 0, 1

        for time in lectures:
            if cum_time + time > mid:
                cnt+=1
                cum_time = 0

            cum_time += time
        
        if cnt <= m:
            end = mid
        elif cnt > m:
            start = mid
            
    print(end)