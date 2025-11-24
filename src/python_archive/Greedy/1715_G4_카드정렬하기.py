import heapq
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    if n==1:
        print(0)
    else:
        lst = []
        for i in range(n):
            heapq.heappush(lst,int(input()))
        total_cost, cost = 0, 0
        while True:
            if len(lst) <= 1:
                break
            else:
                val1 = heapq.heappop(lst)
                val2 = heapq.heappop(lst)
                cost = val1+val2
                total_cost += (cost)
                heapq.heappush(lst,cost)
                cost = 0
        print(total_cost)