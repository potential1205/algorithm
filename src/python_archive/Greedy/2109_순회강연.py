import heapq

if __name__ == "__main__":
    n=int(input())
    lst=[]
    for i in range(n):
        cost,day = map(int, input().split())
        lst.append([day,cost])

    lst.sort()

    result = []
    for i in range(n):
        day,cost = lst[i]
        heapq.heappush(result,cost)

        if day < len(result):
            heapq.heappop(result)
          
    print(sum(result))


