import sys
input = sys.stdin.readline
import heapq

if __name__ == "__main__":
    n = int(input())
    lst = []
    for i in range(n):
        line = list(map(int,input().split()))
        heapq.heappush(lst,(line[1],line[0]))

    cnt = 0
    std = -1
    while lst:
        end,start = heapq.heappop(lst)
        if std <= start:
            cnt+=1
            std = end

    print(cnt)