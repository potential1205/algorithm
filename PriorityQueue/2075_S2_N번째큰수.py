
import sys
input = sys.stdin.readline
import heapq

if __name__ == "__main__":
    n = int(input())
    q = []
    for i in range(n):
        line = list(map(int, input().split()))
        if not q:
            for num in line:
                heapq.heappush(q, num)
        else:
            for num in line:
                if q[0] < num:
                    heapq.heappush(q, num)
                    heapq.heappop(q)
    print(q[0])
