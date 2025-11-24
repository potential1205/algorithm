import sys
import heapq
input = sys.stdin.readline

if __name__ == "__main__":
    n,k = map(int,input().split())
    info = []
    for i in range(n):
        m,v = map(int,input().split())
        heapq.heappush(info,[m,v])
 
    bags = []
    for i in range(k):
        heapq.heappush(bags,int(input()))

    result, answer = [], 0
    for i in range(k):
        bag = heapq.heappop(bags)

        while info and bag >= info[0][0]:
            heapq.heappush(result,-heapq.heappop(info)[1])

        if result:
            answer -= heapq.heappop(result)

    print(answer)
    
