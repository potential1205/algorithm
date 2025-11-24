import sys
input = sys.stdin.readline
import heapq

if __name__ == "__main__":
    n = int(input())
    lines = []
    for i in range(n):
        start,end = map(int,input().split())
        heapq.heappush(lines,(start,-end))
    
    start,end = heapq.heappop(lines)
    answer = [start,-end]
    result = []
    
    while lines:
        start,end = heapq.heappop(lines)
        end = -end
        if start == answer[0]:
            continue

        if start <= answer[1]:
            if end > answer[1]:
                answer[1] = end
        else:
            result.append(answer[1]-answer[0])
            answer[0], answer[1] = start,end
    
    result.append(answer[1]-answer[0])
    
    print(sum(result))
